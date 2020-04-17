package com.wejoyclass.itops.cloud.dto;

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
public class MonitorLicenseDto {

    @ApiModelProperty(notes = "可监控的应用")
    private Integer applicationQuantity;

    @ApiModelProperty(notes = "已使用的监控的存储")
    private Integer usedStorageQuantity;

    @ApiModelProperty(notes = "已使用的监控的web")
    private Integer usedWebQuantity;

    @ApiModelProperty(notes = "已使用的监控的应用")
    private Integer usedApplicationQuantity;

    @ApiModelProperty(notes = "已使用的监控的中间件")
    private Integer usedMiddlewareQuantity;

    @ApiModelProperty(notes = "已使用的监控的数据库")
    private Integer usedDbQuantity;

    @ApiModelProperty(notes = "已使用的监控的网络设备")
    private Integer usedNetworkQuantity;

    @ApiModelProperty(notes = "已使用的监控的操作系统数量")
    private Integer usedOsQuantity;

    @ApiModelProperty(notes = "已使用的监控的硬件数量")
    private Integer usedHardwareQuantity;

    @ApiModelProperty(notes = "可监控的存储")
    private Integer storageQuantity;

    @ApiModelProperty(notes = "可监控的web")
    private Integer webQuantity;

    @ApiModelProperty(notes = "可监控的中间件")
    private Integer middlewareQuantity;

    @ApiModelProperty(notes = "可监控的数据库")
    private Integer dbQuantity;

    @ApiModelProperty(notes = "可监控的网络设备")
    private Integer networkQuantity;

    @ApiModelProperty(notes = "可监控的操作系统数量")
    private Integer osQuantity;

    @ApiModelProperty(notes = "可监控的硬件数量")
    private Integer hardwareQuantity;

    @ApiModelProperty(notes = "失效时间")
    private Date expireTime;

    @ApiModelProperty(notes = "结束日期")
    private Date endDate;

    @ApiModelProperty(notes = "开始日期")
    private Date startDate;

    @ApiModelProperty(notes = "授权码")
    private String licenseCode;

    @ApiModelProperty(notes = "根机构(T_BASE_ORG.ID)")
    private Long orgId;
}
