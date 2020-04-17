package com.wejoyclass.itops.cloud.entity;

import com.wejoyclass.core.service.entity.BaseMysqlEntity;
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
@Table(name = "t_ops_warning_msg_license")
public class WarningMsgLicense extends BaseMysqlEntity {
    //组织id
    private Long orgId;

    //授权码
    private String licenseCode;

    //开始日期
    private Date startDate;

    //结束日期
    private Date endDate;

    //过期时间
    private Date expireTime;

    //是否有微信
    private Long isWx;

    //是否有邮箱
    private Long isEmail;

    //是否有短信
    private Long isMsg;

    //短信通知数量
    private Long msgQuantity;

    //是否有电话
    private Long isPhone;

    //电话通知数量
    private Long phoneQuantity;

    //已使用的短信通知数量
    private Long usedMsgQuantity;

    //已使用的电话通知数量
    private Long usedPhoneQuantity;
}
