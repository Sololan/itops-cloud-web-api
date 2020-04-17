package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRuleQueryParam {

    @ApiModelProperty(notes = "告警级别（字典项)")
    private Long warningLevel;

    @ApiModelProperty(notes = "告警阶段（字典项）")
    private Long warningStage;

    @ApiModelProperty(notes = "通知时间（1：任何时间 2：非工作时间 3：工作时间，周一到周五8：30-17：30）")
    private Integer noticeTiming;

    @ApiModelProperty(notes = "通知方式id")
    private Integer noticeWay;

    @ApiModelProperty(notes = "通知人")
    private String noticeUser;

    @ApiModelProperty(notes = "组织id")
    private Long orgId;

}
