package com.wejoyclass.itops.cloud.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/13 18:25
 **/
@Getter
@Setter
public class MonitorWarningQueryParam {

    @ApiModelProperty(notes = "开始时间的时间范围")
    private String startStartTime;

    @ApiModelProperty(notes = "开始时间的时间范围")
    private String startEndTime;

    @ApiModelProperty(notes = "结束时间的时间范围")
    private String endStartTime;

    @ApiModelProperty(notes = "结束时间的时间范围")
    private String endEndTime;

    @ApiModelProperty(notes = "状态，是否过期")
    private String status;

    private String orgId;
}
