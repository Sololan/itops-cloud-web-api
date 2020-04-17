package com.wejoyclass.itops.cloud.entity;

import com.wejoyclass.core.service.entity.BaseMysqlEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
* 通知规则
* @author shixs
* date 2020-1-14
*/
@Table(name = "t_ops_notice_rule")
@Getter
@Setter
public class NoticeRule extends BaseMysqlEntity {

    // 根机构(T_BASE_ORG.ID)
    private Long orgId;

    // 告警级别（字典项）
    private Long warningLevel;

    // 告警阶段（字典项）
    private Long warningStage;

    // 通知时间（1：任何时间 2：非工作时间 3：工作时间，周一到周五8：30-17：30）
    private Integer noticeTiming;

    // 延迟策略（1：立刻 2：延迟5分钟 3：延迟10分钟）
    private Integer postponementStrategy;

    //通知方式id list
    @Transient
    private List<NoticeWay> noticeWayList;
    //通知人list
    @Transient
    private List<NoticeUser> noticeUserList;

}
