package com.wejoyclass.itops.cloud.entity;

import com.wejoyclass.core.service.entity.BaseMysqlEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Table;
import java.util.Date;

/**
* 通知方式
* @author shixs
* date 2020-1-14
*/
@Table(name = "t_ops_notice_way")
@Getter
@Setter
public class NoticeWay extends BaseMysqlEntity {

    // 通知规则ID
    private Long noticeRuleId;

    // 通知方式（字典项）
    private Long noticeMethod;

}
