package com.wejoyclass.itops.cloud.entity;

import com.wejoyclass.core.service.entity.BaseMysqlEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.util.Date;

/**
 * 工作时间设置实体
 */
@Table(name = "t_ops_worktime")
@Getter
@Setter
public class WorkTime extends BaseMysqlEntity {

    private Long orgId;
    private String week;
    private Date startTime;
    private Date endTime;

}
