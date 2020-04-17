package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author lixu
 * @Date 2020/1/14 10:05
 * @Version 1.0
 */
@Setter
@Getter
public class WarningQueryParam {

    //告警级别id
    @ApiModelProperty(value = "告警级别id")
    private Long warningLevel;

    //告警内容
    @ApiModelProperty(value = "告警内容")
    private String warningInfo;

    //IP地址
    @ApiModelProperty(value = "IP地址")
    private String ipAddress;

    //告警起始时间
    @ApiModelProperty(value = "告警起始时间")
    private String startTime;

    //告警终止时间
    @ApiModelProperty(value = "告警终止时间")
    private String endTime;

    //状态
    @ApiModelProperty(value = "状态")
    private String warningStatus;

    //组织机构id
    @ApiModelProperty(value = "组织机构id")
    private Long orgId;

}
