package com.wejoyclass.itops.cloud.entity;

import com.wejoyclass.core.service.entity.BaseMysqlEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Author lixu
 * @Date 2020/1/9 15:26
 * @Version 1.0
 */

@Table(name = "t_ops_warning_statistics")
@Setter
@Getter
public class WarningStatistic extends BaseMysqlEntity {

    //根机构（T_BASE_ORG.ID）
    private Long orgId;

    //IP地址
    private String ipAddress;

    //报警日期
    private Date warningDay;

    //告警内容
    private String warningInfo;

    //告警级别(字典项)
    private Long warningLevel;

    //告警数量
    private String warningSum;

    //告警内容相同的告警数量
    @Transient
    private String warningInfoCount;
}
