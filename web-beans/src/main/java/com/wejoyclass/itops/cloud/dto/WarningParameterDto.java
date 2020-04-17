package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author lixu
 * @Date 2020/1/16 18:43
 * @Version 1.0
 */
@Setter
@Getter
public class WarningParameterDto {

    //起始时间
    @ApiModelProperty(value = "起始时间")
    private String startTime;

    //终止时间
    @ApiModelProperty(value = "终止时间")
    private String endTime;

    //前N条数据
    @ApiModelProperty(value = "前N条数据")
    private Integer topNumber;

    //组织机构id
    @ApiModelProperty(value = "组织机构id")
    private Long orgId;

}
