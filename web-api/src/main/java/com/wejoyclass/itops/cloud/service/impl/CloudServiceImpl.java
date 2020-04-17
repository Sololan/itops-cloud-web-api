package com.wejoyclass.itops.cloud.service.impl;

import com.wejoyclass.core.util.DateUtil;
import com.wejoyclass.core.util.MapperUtil;
import com.wejoyclass.core.util.RedisUtil;
import com.wejoyclass.itops.cloud.dto.ActiveLicenseDto;
import com.wejoyclass.itops.cloud.dto.InitDataDto;
import com.wejoyclass.itops.cloud.dto.MessageContentDto;
import com.wejoyclass.itops.cloud.dto.MessageUsedDto;
import com.wejoyclass.itops.cloud.entity.*;
import com.wejoyclass.itops.cloud.feign.MessageService;
import com.wejoyclass.itops.cloud.feign.UcService;
import com.wejoyclass.itops.cloud.mapper.*;
import com.wejoyclass.itops.cloud.service.CloudService;
import com.wejoyclass.itops.cloud.service.MonitorLicenseService;
import com.wejoyclass.itops.cloud.service.WarningMsgLicenseService;
import com.wejoyclass.itops.cloud.service.WarningService;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/14 14:30
 **/
@Service
@Transactional
@Slf4j
public class CloudServiceImpl implements CloudService {

    @Autowired
    private MonitorLicenseMapper monitorLicenseMapper;
    @Autowired
    private WarningMsgLicenseMapper warningMsgLicenseMapper;
    @Autowired
    private WarningMsgLicenseService warningMsgLicenseService;
    @Autowired
    private ConnectMapper connectMapper;
    @Autowired
    private UcService ucService;
    @Autowired
    private EquipmentMonitorTypeMapper equipmentMonitorTypeMapper;
    @Autowired
    private MonitorTypeMasterMapper monitorTypeMasterMapper;
    @Autowired
    private MonitorTypeSubMapper monitorTypeSubMapper;
    @Autowired
    private MonitorSubMethodMapper monitorSubMethodMapper;
    @Autowired
    private MonitorMethodParamMapper monitorMethodParamMapper;
    @Autowired
    private MonitorLicenseService monitorLicenseService;
    @Autowired
    private WarningService warningService;
    @Autowired
    private NoticeRuleMapper noticeRuleMapper;
    @Autowired
    private WorkTimeMapper workTimeMapper;
    @Autowired
    private MessageService messageService;

    //授权验证更新授权码
    public ActiveLicenseDto verifyAuthorize(Long orgId) {
        ActiveLicenseDto activeLicenseDto = new ActiveLicenseDto();
        activeLicenseDto.setWarningMsgLicense(warningMsgLicenseMapper.findYesterdayCode(orgId));
        activeLicenseDto.setMonitorLicense(monitorLicenseMapper.findYesterdayCode(orgId));
        return activeLicenseDto;
    }

    //保存最后连接时间
    public WarningMsgLicense connectStatus(MonitorLicense monitorLicense, Long orgId) {
        Connect connect = new Connect();
        connect.setOrgId(orgId);
        connect.setLastConnectTime(new Date());
        Long id = connectMapper.getByOrgId(orgId);
        if (id != null) {
            connect.setId(id);
            connectMapper.updateById(connect);
        } else {
            connectMapper.insert(connect);
        }
        monitorLicenseService.update(monitorLicense);
        return warningMsgLicenseMapper.findEntityByOrgId(orgId);
    }

    //验证授权码是否正确，过期
    public Integer verifyLicenseCode(String code) {
        if (monitorLicenseMapper.findEntityByCode(code) == null) {
            return -1;
        } else if (monitorLicenseMapper.findEntityByCode(code).getExpireTime().before(new Date())) {
            return 1;
        } else {
            return 0;
        }
    }

    //初始化数据
    public InitDataDto initData(String code) {

        Long orgId = monitorLicenseMapper.findEntityByCode(code).getOrgId();

        /*获取初始化数据*/
        InitDataDto initDataDto = new InitDataDto();
        /*uc部分*/
        initDataDto.setUser(ucService.getUserByOrgId(orgId).getData());
        initDataDto.setOrg(ucService.get(orgId).getData());
        initDataDto.setDictGroupDtos(ucService.getDictsGroups(1L).getData());
        initDataDto.setRoles(ucService.getRoles(orgId).getData());
        /*bs部分*/
        initDataDto.setEquipmentMonitorTypes(equipmentMonitorTypeMapper.findAll());
        initDataDto.setMonitorLicense(monitorLicenseMapper.findEntityList(orgId));
        initDataDto.setWarningMsgLicense(warningMsgLicenseMapper.findEntityList(orgId));
        initDataDto.setMonitorMethodParams(monitorMethodParamMapper.findAll());
        initDataDto.setMonitorSubMethods(monitorSubMethodMapper.findAll());
        initDataDto.setMonitorTypeMasters(monitorTypeMasterMapper.findAll());
        initDataDto.setMonitorTypeSubs(monitorTypeSubMapper.findAll());
//        initDataDto.setDictGroups(ucService.get);
        return initDataDto;
    }

    /**
     * 同步本地告警信息并添加
     */
    @Override
    public void syncAddWarning(Warning warning) {

        warningService.saveWarning(warning);
        handelMessage(warning);
        //publish to cloud redis
    }

    /**
     * 同步本地告警信息并修改
     */
    @Override
    public void syncUpdateWarning(Warning warning) {
        warningService.updateWarningByCode(warning);
        handelMessage(warningService.getByWarningCode(warning.getWarningCode()));
        //publish to cloud redis
    }


    private void handelMessage(Warning warning) {
        //        warning = warningService.get(15L);
        Long orgId = warning.getOrgId();
        Long id = warning.getId();

        WorkTime workTime = workTimeMapper.getNewWorkTime(orgId);
        if(workTime == null){
            workTime = workTimeMapper.getNewWorkTimeByOrgId(1L);
        }

        WarningMsgLicense warningMsgLicense = warningMsgLicenseMapper.findEntityByOrgId(orgId);

        //获取数据
        List<MessageUsedDto> messageUsedDtos = noticeRuleMapper.getMessageUsedByWarningLevelOrgId(warning.getWarningLevel(), orgId);

        //分组
        Map<GroupSort, List<MessageUsedDto>> groupSortListMap = messageUsedDtos.stream().
                collect(Collectors.groupingBy(messageUsedDto -> new GroupSort(messageUsedDto.getId(), messageUsedDto.getMethodItemKey())));
        //每组处理
        for (GroupSort sort : groupSortListMap.keySet()) {
            MessageUsedDto messageUsedDtoType = groupSortListMap.get(sort).get(0);
            //告警阶段（1发生，2关闭（不发送告警））
            Long stageItemKey = messageUsedDtoType.getStageItemKey();
            // 通知时间（1：任何时间 2：非工作时间 3：工作时间）
            Integer noticeTime = messageUsedDtoType.getNoticeTiming();
            //延时策略
            Long strategy = messageUsedDtoType.getStrategyItemKey() - 1;

            //分组处理发出去的消息
            Warning finalWarning = warning;
            List<MessageEntity> finalMessageEntities = new LinkedList<>();
            groupSortListMap.get(sort).forEach(messageUsedDto -> {
                MessageEntity messageEntity = new MessageEntity();
                // 产生：电话和短信  您xxx设备上的xxx监控，出现xxx问题，请及时处理
                // 恢复：电话和短信  您xxx设备上的xxx监控，xxx问题已恢复
                if (stageItemKey == 1) {
                    //1，电话，2，短信，3，微信，4邮箱
                    if(messageUsedDto.getNoticeMethod() == 2){
                        String content = "您"+finalWarning.getEquipmentCode()+"设备上的"+finalWarning.getMonitorName()+"监控，出现"+finalWarning.getWarningInfo()+"问题，请及时处理";
                        messageEntity.setContent(content);
                    }else {
                        String content = messageUsedDto.getFullName() + "，您好：\n于" + DateUtil.toChar(finalWarning.getWarningTime())+"，在" + finalWarning.getMonitorName() + "监控对象上产生了" +
                                finalWarning.getTriggerName() + "告警，告警编号为：" +  finalWarning.getWarningCode() + "，请及时处理！";
                        messageEntity.setContent(content);
                    }

                } else {
                    if(messageUsedDto.getNoticeMethod() == 2){
                        String content = "您"+finalWarning.getEquipmentCode()+"设备上的"+finalWarning.getMonitorName()+"监控，"+finalWarning.getWarningInfo()+"问题已恢复";
                        messageEntity.setContent(content);
                    }else {
                        String closedContent = messageUsedDto.getFullName() + "，您好：\n于" + DateUtil.toChar(finalWarning.getWarningTime())+"，在" + finalWarning.getMonitorName() + "监控对象上产生的" +
                                finalWarning.getTriggerName() + "告警，已处理！";
                        messageEntity.setContent(closedContent);
                    }
                }
                if (messageUsedDto.getMethodItemKey() != null) {
                    if (messageUsedDto.getMethodItemKey() == 1) {
                        messageEntity.setTo(messageUsedDto.getPhone());
                        messageEntity.setType(1);
                    } else if (messageUsedDto.getMethodItemKey() == 2) {
                        messageEntity.setTo(messageUsedDto.getPhone());
                        messageEntity.setType(2);
                    } else if (messageUsedDto.getMethodItemKey() == 3) {
                        messageEntity.setTo(messageUsedDto.getOpenId());
                        messageEntity.setType(3);
                    } else if (messageUsedDto.getMethodItemKey() == 4) {
                        messageEntity.setTo(messageUsedDto.getEmail());
                        messageEntity.setType(4);
                    }
                } else {
                    return;
                }
                if (!(messageEntity.getType() == 3 && messageEntity.getTo() == null)) {
                    finalMessageEntities.add(messageEntity);
                }
            });

            List<MessageEntity> messageEntities = finalMessageEntities;

            long usedPhone = warningMsgLicense.getUsedPhoneQuantity();
            long usedMsg = warningMsgLicense.getUsedMsgQuantity();
            //判断当前的激活的告警的消息发送方式，以及条数
            if (messageEntities.size() > 0) {
                if (warningMsgLicense.getIsPhone() == 1 && messageEntities.get(0).getType() == 1) {
                    long length = warningMsgLicense.getPhoneQuantity() - warningMsgLicense.getUsedPhoneQuantity();
                    if (length < messageEntities.size()) {
                        messageEntities = messageEntities.subList(0, (int) length);
                        usedPhone = warningMsgLicense.getPhoneQuantity();
                    } else {
                        usedPhone = warningMsgLicense.getUsedPhoneQuantity() + messageEntities.size();
                    }
                } else if (warningMsgLicense.getIsMsg() == 1 && messageEntities.get(0).getType() == 2) {
                    long length = warningMsgLicense.getMsgQuantity() - warningMsgLicense.getUsedMsgQuantity();
                    if (length < messageEntities.size()) {
                        messageEntities = messageEntities.subList(0, (int) length);
                        usedMsg = warningMsgLicense.getMsgQuantity();
                    } else {
                        usedMsg = warningMsgLicense.getUsedMsgQuantity() + messageEntities.size();
                    }
                } else if (warningMsgLicense.getIsWx() == 0 && messageEntities.get(0).getType() == 3) {
                    return;
                } else if (warningMsgLicense.getIsEmail() == 0 && messageEntities.get(0).getType() == 4) {
                    return;
                }
            }

            //修改已使用条数
            warningMsgLicense.setUsedMsgQuantity(usedMsg);
            warningMsgLicense.setUsedPhoneQuantity(usedPhone);
            warningMsgLicenseService.update(warningMsgLicense);

            List<String> weeks = Arrays.asList(workTime.getWeek().split(","));
            //处理工作时间
            boolean isToday = weeks.contains(getWeek(0));
            boolean isNow = false;
            int toWorkDay = 0;

            if (isToday) {
                if (strategy == 0) {
                    if (DateUtil.toChar(new Date(), "HH:mm:ss").compareTo(DateUtil.toChar(workTime.getStartTime(), "HH:mm:ss")) >= 0
                            && DateUtil.toChar(new Date(), "HH:mm:ss").compareTo(DateUtil.toChar(workTime.getEndTime(), "HH:mm:ss")) <= 0) {
                        isNow = true;
                    }
                } else if (strategy == 1) {
                    if (DateUtil.toChar(DateUtil.addMinutes(new Date(), 5), "HH:mm:ss").compareTo(DateUtil.toChar(workTime.getStartTime(), "HH:mm:ss")) >= 0
                            && DateUtil.toChar(DateUtil.addMinutes(new Date(), 5), "HH:mm:ss").compareTo(DateUtil.toChar(workTime.getEndTime(), "HH:mm:ss")) <= 0) {
                        isNow = true;
                    }
                } else {
                    if (DateUtil.toChar(DateUtil.addMinutes(new Date(), 10), "HH:mm:ss").compareTo(DateUtil.toChar(workTime.getStartTime(), "HH:mm:ss")) >= 0
                            && DateUtil.toChar(DateUtil.addMinutes(new Date(), 10), "HH:mm:ss").compareTo(DateUtil.toChar(workTime.getEndTime(), "HH:mm:ss")) <= 0) {
                        isNow = true;
                    }
                }
            } else {
                for (int i = 1; i < 8; i++) {
                    if (weeks.contains(getWeek(i))) {
                        toWorkDay = i;
                        break;
                    }
                }
            }

            Date nextWorkDay = localDateTime2Date(LocalDateTime.parse(DateUtil.toChar(DateUtil.addDays(new Date(), toWorkDay), "yyyy-MM-dd") + " " + DateUtil.toChar(workTime.getStartTime(), "HH:mm:ss"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Date restTime = localDateTime2Date(LocalDateTime.parse(DateUtil.toChar(new Date(), "yyyy-MM-dd") + " " + DateUtil.toChar(workTime.getEndTime(), "HH:mm:ss"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            long fiveMinutes = 5 * 60;
            long tenMinutes = 10 * 60;
            long secondBetweenWorkDay = nextWorkDay.getTime() - new Date().getTime() - strategy * fiveMinutes;
            long secondBetweenRestTime = restTime.getTime() - new Date().getTime() - strategy * fiveMinutes;
            //redis
            MessageContentDto messageContentDto = new MessageContentDto();
            messageContentDto.setMessageEntityList(messageEntities);
            messageContentDto.setId(warning.getId());
            if (messageEntities.size() > 0) {
                //判断发送规则
                //发送
                if (stageItemKey == 1) {
                    if (noticeTime == 1) {
                        if (strategy == 0) {
                            messageService.putMessage(messageContentDto.getMessageEntityList());
                        } else {
                            messageContentDto.setSendTime(0L);
                            RedisUtil.put(MapperUtil.convertToJson(messageContentDto), "message", strategy * fiveMinutes);
                        }
                    } else if (noticeTime == 2) {
                        if (!isNow) {
                            if (strategy == 0) {
                                messageService.putMessage(messageContentDto.getMessageEntityList());
                            } else {
                                messageContentDto.setSendTime(0L);
                                RedisUtil.put(MapperUtil.convertToJson(messageContentDto), "message", strategy * fiveMinutes);
                            }
                        } else {
                            messageContentDto.getMessageEntityList().forEach(messageEntity -> {
                                messageEntity.setDate(restTime);
                            });
                            messageContentDto.setSendTime(secondBetweenRestTime);
                            if (strategy == 0) {
                                messageService.putMessage(messageContentDto.getMessageEntityList());
                            } else {
                                messageContentDto.setSendTime(0L);
                                RedisUtil.put(MapperUtil.convertToJson(messageContentDto), "message", strategy * fiveMinutes);
                            }
                        }
                    } else if (noticeTime == 3) {
                        if (isNow) {
                            if (strategy == 0) {
                                messageService.putMessage(messageContentDto.getMessageEntityList());
                            } else {
                                messageContentDto.setSendTime(0L);
                                RedisUtil.put(MapperUtil.convertToJson(messageContentDto), "message", strategy * fiveMinutes);
                            }
                        } else {
                            messageContentDto.getMessageEntityList().forEach(messageEntity -> {
                                messageEntity.setDate(nextWorkDay);
                            });
                            messageContentDto.setSendTime(secondBetweenWorkDay);
                            if (strategy == 0) {
                                messageService.putMessage(messageContentDto.getMessageEntityList());
                            } else {
                                messageContentDto.setSendTime(0L);
                                RedisUtil.put(MapperUtil.convertToJson(messageContentDto), "message", strategy * fiveMinutes);
                            }
                        }
                    }
                } else {
                    if (noticeTime == 1) {
                        if (strategy == 0) {
                            messageService.putMessage(messageContentDto.getMessageEntityList());
                        }
                    } else if (noticeTime == 2) {
                        if (!isNow) {
                            if (strategy == 0) {
                                messageService.putMessage(messageContentDto.getMessageEntityList());
                            }
                        } else {
                            messageContentDto.getMessageEntityList().forEach(messageEntity -> {
                                messageEntity.setDate(restTime);
                            });
                            messageService.putMessage(messageContentDto.getMessageEntityList());
                        }
                    } else if (noticeTime == 3) {
                        if (isNow) {
                            if (strategy == 0) {
                                messageService.putMessage(messageContentDto.getMessageEntityList());
                            }
                        } else {
                            messageContentDto.getMessageEntityList().forEach(messageEntity -> {
                                messageEntity.setDate(nextWorkDay);
                            });
                            messageService.putMessage(messageContentDto.getMessageEntityList());
                        }
                    }
                }
            }
            log.info(MapperUtil.convertToJson(messageContentDto.getMessageEntityList()));
        }
    }


    //分组使用
    @EqualsAndHashCode
    static class GroupSort {
        private Long id;
        private Long methodItemKey;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getMethodItemKey() {
            return methodItemKey;
        }

        public void setMethodItemKey(Long methodItemKey) {
            this.methodItemKey = methodItemKey;
        }

        public GroupSort(Long id, Long methodItemKey) {
            this.id = id;
            this.methodItemKey = methodItemKey;
        }
    }

    private String getWeek(Integer days) {

        String[] weekDays = {"周六", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        return weekDays[(calendar.get(Calendar.DAY_OF_WEEK) + days) % 7];
    }

    private Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        return Date.from(zdt.toInstant());
    }
}
