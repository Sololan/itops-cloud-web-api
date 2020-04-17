package com.wejoyclass.itops.cloud.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorSubMethod {
    @ApiModelProperty(notes = "id")
    private String id;

    @ApiModelProperty(notes = "监控子类型id")
    private String monitorTypeSubId;

    @ApiModelProperty(notes = "监控方式")
    private String methodName;

    @ApiModelProperty(notes = "排序")
    private Integer sortNum;
}
