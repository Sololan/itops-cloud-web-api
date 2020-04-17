package com.wejoyclass.itops.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TenantOrgDto{

    //org
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("父id")
    private Long pid;

    @ApiModelProperty("组织全称")
    private String fullName;

    @ApiModelProperty("组织简称")
    private String simpleName;

    @ApiModelProperty("组织编码")
    private String code;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("成立日期")
    private Date date;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("LOGO的ID")
    private Long logoId;

    @ApiModelProperty("组织联系人")
    private String contact;

    @ApiModelProperty("组织邮箱")
    private String email;

    @ApiModelProperty("组织地址")
    private String address;

    @ApiModelProperty("状态")
    private Integer status;

    //role
    @ApiModelProperty("角色id")
    private Long rid;

    @ApiModelProperty("角色姓名")
    private String rname;

    @ApiModelProperty("角色编码")
    private String rcode;

    @ApiModelProperty("是否启用")
    private Integer renable;

    @ApiModelProperty("组织ID")
    private String orgId;

    @ApiModelProperty("排序")
    private String rsort;

    @ApiModelProperty("备注")
    private String rremarks;
}
