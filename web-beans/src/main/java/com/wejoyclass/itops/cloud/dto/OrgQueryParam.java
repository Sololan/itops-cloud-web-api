package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/17 10:37
 **/
@Getter
@Setter
public class OrgQueryParam {

    @ApiModelProperty(notes = "组织名")
    private String fullName;

    @ApiModelProperty(notes = "连接状态")
    private Long connectStatus;

    @ApiModelProperty(notes = "联系人")
    private String contact;

    @ApiModelProperty(notes = "开始时间")
    private String startTime;

    @ApiModelProperty(notes = "结束时间")
    private String endTime;
}
