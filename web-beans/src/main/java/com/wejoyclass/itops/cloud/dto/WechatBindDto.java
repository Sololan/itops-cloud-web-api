package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WechatBindDto {
    @ApiModelProperty(notes = "用户名")
    private String username;

    @ApiModelProperty(notes = "微信code")
    private String code;
}
