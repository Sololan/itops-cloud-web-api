package com.wejoyclass.itops.cloud.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.wejoyclass.uc.entity.Org;

import java.util.Date;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/7 16:52
 **/
@Getter
@Setter
public class OrgDto {

    @ApiModelProperty(notes = "组织id")
    private Long id;

    @ApiModelProperty(notes = "最后连接时间")
    private Date lastConnectTime;

    @ApiModelProperty(notes = "连接状态")
    private Long connectStatus;

    @ApiModelProperty(notes = "组织全名")
    private String fullName;

    @ApiModelProperty(notes = "联系人")
    private String contact;

    @ApiModelProperty(notes = "电话号")
    private String phone;

    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

    @ApiModelProperty(notes = "状态")
    private Long status;
}
