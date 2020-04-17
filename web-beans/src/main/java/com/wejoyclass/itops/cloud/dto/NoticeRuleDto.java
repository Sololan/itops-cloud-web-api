package com.wejoyclass.itops.cloud.dto;

import com.wejoyclass.itops.cloud.entity.NoticeUser;
import com.wejoyclass.itops.cloud.entity.NoticeWay;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeRuleDto {

    @ApiModelProperty(notes = "主键")
    @NotNull
    private Long id;

    @ApiModelProperty(notes = "根机构(T_BASE_ORG.ID)")
    @NotNull
    private Long orgId;

    @ApiModelProperty(notes = "告警级别（字典项）")
    @NotNull
    private Long warningLevel;

    @ApiModelProperty(notes = "告警阶段（字典项）")
    @NotNull
    private Long warningStage;

    @ApiModelProperty(notes = "通知时间（1：任何时间 2：非工作时间 3：工作时间，周一到周五8：30-17：30）")
    @NotNull
    private Integer noticeTiming;

    @ApiModelProperty(notes = "延迟策略（1：立刻 2：延迟5分钟 3：延迟10分钟）")
    @NotNull
    private Integer postponementStrategy;

    @ApiModelProperty(notes = "通知方式id list")
    @Transient
    private List<NoticeWay> noticeWayList;

    @ApiModelProperty(notes = "通知人list")
    @Transient
    private List<NoticeUser> noticeUserList;

}
