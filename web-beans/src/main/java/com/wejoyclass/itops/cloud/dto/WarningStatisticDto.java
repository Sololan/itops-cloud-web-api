package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WarningStatisticDto {

    // 告警级别
    @ApiModelProperty(value = "告警级别")
    private Integer warningLevel;

    //告警级别名
    @ApiModelProperty(value = "告警级别名")
    private String warningLevelName;

    // 告警数量
    @ApiModelProperty(value = "告警数量")
    private Integer warningSum;


}
