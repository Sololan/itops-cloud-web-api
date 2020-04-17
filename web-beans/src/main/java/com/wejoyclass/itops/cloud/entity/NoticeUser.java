package com.wejoyclass.itops.cloud.entity;

import com.wejoyclass.core.service.entity.BaseMysqlEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
* 通知人
* @author shixs
* date 2020-1-14
*/
@Table(name = "t_ops_notice_user")
@Getter
@Setter
public class NoticeUser extends BaseMysqlEntity {

    // 通知规则ID
    private Long noticeRuleId;

    // 通知人（用户表）
    private Long noticeUser;

    //通知人姓名
    @Transient
    private String fullName;

}
