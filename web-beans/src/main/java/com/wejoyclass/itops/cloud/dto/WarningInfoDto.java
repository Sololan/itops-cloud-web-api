package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author lixu
 * @Date 2020/1/13 15:27
 * @Version 1.0
 */
@Setter
@Getter
public class WarningInfoDto {

    //告警内容
    @ApiModelProperty(value = "告警内容")
    private String warningInfo;

    //告警内容频率
    @ApiModelProperty(value = "告警内容频率")
    private Integer warningInfoCount;

}
