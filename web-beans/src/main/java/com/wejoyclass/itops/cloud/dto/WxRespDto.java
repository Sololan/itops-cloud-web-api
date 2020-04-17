package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class WxRespDto {
    @ApiModelProperty(notes = "accessToken")
    private String accessToken;

    @ApiModelProperty(notes = "expiresIn")
    private Long expiresIn;

    @ApiModelProperty(notes = "refreshToken")
    private String refreshToken;

    @ApiModelProperty(notes = "openid")
    private String openid;

    @ApiModelProperty(notes = "scope")
    private String scope;

    @ApiModelProperty(notes = "errcode")
    private String errcode;

    @ApiModelProperty(notes = "errmsg")
    private String errmsg;
}
