package com.wejoyclass.itops.cloud.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 20:27 2020/2/18
 * @Modified By:
 **/
@Getter
@Setter
public class MonitorMethodParam {

    @ApiModelProperty(notes = "id")
    private Long id;

    @ApiModelProperty(notes = "监控分类监控方式id")
    private Long monitorMethodId;

    @ApiModelProperty(notes = "参数code")
    private String paramCode;

    @ApiModelProperty(notes = "参数名称")
    private String paramName;

    @ApiModelProperty(notes = "默认值")
    private String defaultValue;

    @ApiModelProperty(notes = "排序")
    private Integer sortNum;

    @ApiModelProperty(notes = "是否为必须")
    private Integer isRequire;

}
