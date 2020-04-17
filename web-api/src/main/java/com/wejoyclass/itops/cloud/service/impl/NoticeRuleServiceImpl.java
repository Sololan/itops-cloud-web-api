package com.wejoyclass.itops.cloud.service.impl;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.util.ExceptionUtil;
import com.wejoyclass.core.util.PageUtil;
import com.wejoyclass.itops.cloud.dto.NoticeRuleQueryParam;
import com.wejoyclass.itops.cloud.entity.NoticeRule;
import com.wejoyclass.itops.cloud.entity.NoticeUser;
import com.wejoyclass.itops.cloud.entity.NoticeWay;
import com.wejoyclass.itops.cloud.mapper.WechatMapper;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;
import com.wejoyclass.itops.cloud.mapper.*;
import com.wejoyclass.itops.cloud.service.NoticeRuleService;
import com.wejoyclass.service.impl.BaseCURDServiceImpl;
import com.wejoyclass.service.util.EntityUtil;
import com.wejoyclass.service.util.SecurityUtil;
import com.wejoyclass.uc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 通知规则service
 */
@Service
@Transactional
public class NoticeRuleServiceImpl extends BaseCURDServiceImpl<NoticeRule, Long> implements NoticeRuleService {

    @Autowired
    private NoticeRuleMapper noticeRuleMapper;

    @Autowired
    private NoticeWayMapper noticeWayMapper;

    @Autowired
    private NoticeUserMapper noticeUserMapper;

    @Autowired
    private WarningMsgLicenseMapper warningMsgLicenseMapper;

    @Autowired
    private WechatMapper weChatMapper;

    @Override
    public List<NoticeRuleQueryParam> findNoticeRuleList(NoticeRuleQueryParam noticeRuleDto) {
        return noticeRuleMapper.findNoticeRuleList(noticeRuleDto);
    }

    @Override
    public Page<NoticeRuleQueryParam> findNoticeRulePagesByContidion(QueryParameter queryParameter, NoticeRuleQueryParam noticeRuleDto) {
        return PageUtil.process(queryParameter,() -> findNoticeRuleList(noticeRuleDto));
    }

    @Override
    public NoticeRule getNoticeRuleById(Long id) {
        return noticeRuleMapper.getNoticeRuleById(id);
    }

    @Override
    public WarningMsgLicense getNoticeWayByOrgId(Long orgId) {
        return warningMsgLicenseMapper.getBefore(orgId);
    }


    @Override
    public NoticeRule insert(NoticeRule noticeRule) {
        //获取通知方式
        List<NoticeWay> noticeWay = noticeRule.getNoticeWayList();
        //获取通知人
        List<NoticeUser> noticeUsers = noticeRule.getNoticeUserList();

        //获取没绑定微信的用户
        for (NoticeWay way : noticeWay) {
            if (way.getNoticeMethod() == 3) {   //WeChat id
                if (noticeUsers.size() > 0) {
                    List<Long> userIds = noticeUsers.stream()
                            .map(NoticeUser::getNoticeUser)
                            .collect(toList());
                    List<User> unboundUsers = weChatMapper.getUnboundUsers(userIds, SecurityUtil.getTopOrgId());
                    if (unboundUsers.size() > 0) {
                        StringBuilder fullNames = new StringBuilder();
                        for (int i = 0; i < unboundUsers.size(); i++) {
                            if (i > 0) {
                                if (i == unboundUsers.size() - 1) {
                                    fullNames.append("和");
                                } else {
                                    fullNames.append("、");
                                }
                            }
                            fullNames.append(unboundUsers.get(i).getFullName());
                        }
                        throw ExceptionUtil.msg(HttpStatus.FORBIDDEN.value(), String.format("%s没有绑定公众号，收不到微信通知！", fullNames.toString()));
                    }
                }
                break;
            }
        }

        noticeRule.setOrgId(SecurityUtil.getTopOrgId());
        EntityUtil.setCreate(noticeRule);
        //保存通知规则
        super.insert(noticeRule);

        //保存通知方式
        if (noticeWay.size() > 0){
            saveNoticeWays(noticeRule.getId(),noticeWay);
        }

        //保存通知人
        if (noticeUsers.size() > 0) {
            saveNoticeUsers(noticeRule.getId(),noticeUsers);
        }
        return noticeRule;
    }

    @Override
    public void update(NoticeRule noticeRule) {
        //获取通知方式
        List<NoticeWay> noticeWay = noticeRule.getNoticeWayList();
        //获取通知人
        List<NoticeUser> noticeUsers = noticeRule.getNoticeUserList();

        //获取没绑定微信的用户
        for (NoticeWay way : noticeWay) {
            if (way.getNoticeMethod() == 3) {   //WeChat id
                if (noticeUsers.size() > 0) {
                    List<Long> userIds = noticeUsers.stream()
                            .map(NoticeUser::getNoticeUser)
                            .collect(toList());
                    List<User> unboundUsers = weChatMapper.getUnboundUsers(userIds, SecurityUtil.getTopOrgId());
                    if (unboundUsers.size() > 0) {
                        StringBuilder fullNames = new StringBuilder();
                        for (int i = 0; i < unboundUsers.size(); i++) {
                            if (i > 0) {
                                if (i == unboundUsers.size() - 1) {
                                    fullNames.append("和");
                                } else {
                                    fullNames.append("、");
                                }
                            }
                            fullNames.append(unboundUsers.get(i).getFullName());
                        }
                        throw ExceptionUtil.msg(HttpStatus.FORBIDDEN.value(), String.format("%s没有绑定公众号，收不到微信通知！", fullNames.toString()));
                    }
                }
                break;
            }
        }

        super.update(noticeRule);
        //删除通知方式
        noticeWayMapper.deleteNoticeWayByRuleId(noticeRule.getId());
        //删除通知人
        noticeUserMapper.deleteNoticeUserByRuleId(noticeRule.getId());

        //保存通知方式
        if (noticeWay.size() > 0) {
            saveNoticeWays(noticeRule.getId(),noticeWay);
        }

        //保存通知人
        if (noticeUsers.size() > 0) {
            saveNoticeUsers(noticeRule.getId(),noticeUsers);
        }
    }

    /**
     * 保存通知方式
     * @param noticeRuleId
     * @param noticeWayList
     */
    public void saveNoticeWays(Long noticeRuleId, List<NoticeWay> noticeWayList){

        noticeWayList.stream().forEach((e) -> {
            e.setNoticeRuleId(noticeRuleId);
            EntityUtil.setCreate(e);
        });

        noticeWayMapper.insertList(noticeWayList);
    }

    /**
     * 保存通知人
     */
    public void saveNoticeUsers(Long noticeRuleId, List<NoticeUser> noticeUserList){

        noticeUserList.stream().forEach((e) -> {
            e.setNoticeRuleId(noticeRuleId);
            EntityUtil.setCreate(e);
        });
        noticeUserMapper.insertList(noticeUserList);
    }

    @Override
    public void deleteLogic(NoticeRule noticeRule) {
        //删除通知方式
        noticeWayMapper.deleteNoticeWayByRuleId(noticeRule.getId());
        //删除通知人
        noticeUserMapper.deleteNoticeUserByRuleId(noticeRule.getId());
        //删除通知规则
        super.deleteLogic(noticeRule);
    }
}
