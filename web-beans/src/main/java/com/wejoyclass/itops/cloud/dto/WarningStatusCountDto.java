package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WarningStatusCountDto {

    // 告警状态
    @ApiModelProperty(value = "告警状态")
    private Integer warningStatus;

    // 告警状态统计数量
    @ApiModelProperty(value = "告警状态统计数量")
    private Integer statusSum;
}
