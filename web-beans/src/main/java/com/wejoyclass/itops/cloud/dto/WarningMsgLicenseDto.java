package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 18:24
 **/
@Getter
@Setter
public class WarningMsgLicenseDto {

    @ApiModelProperty(notes = "组织id")
    private Long orgId;

    @ApiModelProperty(notes = "授权码")
    private String licenseCode;

    @ApiModelProperty(notes = "开始日期")
    private Date startDate;

    @ApiModelProperty(notes = "结束日期")
    private Date endDate;

    @ApiModelProperty(notes = "过期时间")
    private Date expireTime;

    @ApiModelProperty(notes = "是否有微信")
    private Long isWx;

    @ApiModelProperty(notes = "是否有邮箱")
    private Long isEmail;

    @ApiModelProperty(notes = "是否有短信")
    private Long isMsg;

    @ApiModelProperty(notes = "短信通知数量")
    private Long msgQuantity;

    @ApiModelProperty(notes = "是否有电话")
    private Long isPhone;

    @ApiModelProperty(notes = "电话通知数量")
    private Long phoneQuantity;

    @ApiModelProperty(notes = "已使用的短信通知数量")
    private Long usedMsgQuantity;

    @ApiModelProperty(notes = "已使用的电话通知数量")
    private Long usedPhoneQuantity;
}
