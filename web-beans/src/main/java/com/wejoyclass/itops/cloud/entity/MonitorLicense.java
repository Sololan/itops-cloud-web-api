package com.wejoyclass.itops.cloud.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wejoyclass.core.service.entity.BaseMysqlEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 14:38
 **/
@Getter
@Setter
@Table(name="t_ops_monitor_license")
public class MonitorLicense extends BaseMysqlEntity {
    // 可监控的应用
    private Integer applicationQuantity;

    // 已使用的监控的存储
    private Integer usedStorageQuantity;

    // 已使用的监控的web
    private Integer usedWebQuantity;

    // 已使用的监控的应用
    private Integer usedApplicationQuantity;

    // 已使用的监控的中间件
    private Integer usedMiddlewareQuantity;

    // 已使用的监控的数据库
    private Integer usedDbQuantity;

    // 已使用的监控的网络设备
    private Integer usedNetworkQuantity;

    // 已使用的监控的操作系统数量
    private Integer usedOsQuantity;

    // 已使用的监控的硬件数量
    private Integer usedHardwareQuantity;

    // 可监控的存储
    private Integer storageQuantity;

    // 可监控的web
    private Integer webQuantity;

    // 可监控的中间件
    private Integer middlewareQuantity;

    // 可监控的数据库
    private Integer dbQuantity;

    // 可监控的网络设备
    private Integer networkQuantity;

    // 可监控的操作系统数量
    private Integer osQuantity;

    // 可监控的硬件数量
    private Integer hardwareQuantity;

    // 失效时间
    private Date expireTime;

    // 结束日期
    private Date endDate;

    // 开始日期
    private Date startDate;

    // 授权码
    private String licenseCode;

    // 根机构(T_BASE_ORG.ID)
    private Long orgId;
}
