package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRuleQueryDto {

    @ApiModelProperty(notes = "通知规则id")
    private Long id;

    @ApiModelProperty(notes = "告警级别（字典项)")
    private Long warningLevel;
    @ApiModelProperty(notes = "告警级别名称")
    private String warningLevelNAME;

    @ApiModelProperty(notes = "告警阶段（字典项）")
    private Long warningStage;

    @ApiModelProperty(notes = "告警阶段名称")
    private String warningsTageNAME;

    @ApiModelProperty(notes = "通知时间（1：任何时间 2：非工作时间 3：工作时间，周一到周五8：30-17：30）")
    private Integer noticeTiming;

    @ApiModelProperty(notes = "延迟策略（1：立刻 2：延迟5分钟 3：延迟10分钟）")
    private Integer postponementStrategy;

    @ApiModelProperty(notes = "延迟策略名称")
    private String postPoneMentStrategyNAME;

    @ApiModelProperty(notes = "通知方式id")
    private Integer noticeWay;

    @ApiModelProperty(notes = "通知方式(多个以逗号拼接)")
    private String noticeMethod;

    @ApiModelProperty(notes = "通知人")
    private String noticeUser;

    @ApiModelProperty(notes = "组织id")
    private Long orgId;

}
