package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkTimeDto {

    @ApiModelProperty(notes = "主键")
    @NotNull
    private Long id;

    @ApiModelProperty(notes = "根机构(T_BASE_ORG.ID)")
    @NotNull
    private Long orgId;

    @ApiModelProperty(notes = "周几(1：周一，2：周二，3：周三。。。7：周日)多个之间用半角逗号分隔")
    @NotNull
    private String week;

    @ApiModelProperty(notes = "开始时间")
    @NotNull
    private Date startTime;

    @ApiModelProperty(notes = "结束时间")
    @NotNull
    private Date endTime;

}
