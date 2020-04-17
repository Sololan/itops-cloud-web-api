package com.wejoyclass.itops.cloud.entity;

import com.wejoyclass.core.service.entity.BaseMysqlEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @Author lixu
 * @Date 2020/1/9 15:26
 * @Version 1.0
 */

@Table(name = "t_ops_warning")
@Setter
@Getter
public class Warning extends BaseMysqlEntity {

    //根机构（T_BASE_ORG.ID）
    private Long orgId;

    //告警编号
    private String warningCode;

    //告警级别(字典项)
    private Long warningLevel;

    //告警内容
    private String warningInfo;

    //IP地址
    private String ipAddress;

    //告警时间
    private Date warningTime;

    //告警状态
    private String warningStatus;

    //关闭时间
    private Date closeTime;

    //设备ID
    private Long equipmentId;

    //监控ID
    private Long monitorId;

    //设备code
    private String equipmentCode;

    //监控名称
    private String monitorName;

    //触发器名称
    @Transient
    private String triggerName;

    //报警通知用户列表
    @Transient
    private String users;

    //告警级别的名称
    @Transient
    private String warningLevelName;

}
