package com.wejoyclass.itops.cloud.dto;

import com.wejoyclass.itops.cloud.entity.*;
import com.wejoyclass.uc.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/14 11:15
 **/
@Getter
@Setter
public class InitDataDto {
    //    组织实体
    @ApiModelProperty(notes = "组织实体")
    private Org org;

    //    字典项实体
    @ApiModelProperty(notes = "字典项实体")
    private List<DictItem> dictItems;

    //    字典组实体
    @ApiModelProperty(notes = "字典组实体")
    private List<DictGroup> dictGroups;

    //    用户实体
    @ApiModelProperty(notes = "用户实体")
    private List<User> user;

    @ApiModelProperty(notes = "字典组字典项")
    private List<DictGroupDto> dictGroupDtos;

    @ApiModelProperty(notes = "角色")
    private List<Role> roles;

    /*bs*/
    @ApiModelProperty(notes = "设备监控类型")
    private List<EquipmentMonitorType> equipmentMonitorTypes;

    @ApiModelProperty(notes = "监控类型")
    private List<MonitorTypeMaster> monitorTypeMasters;

    @ApiModelProperty(notes = "监控类型分类")
    private List<MonitorTypeSub> monitorTypeSubs;

    @ApiModelProperty(notes = "监控分类监控方式")
    private List<MonitorSubMethod> monitorSubMethods;

    @ApiModelProperty(notes = "监控方式参数")
    private List<MonitorMethodParam> monitorMethodParams;

    //    监控授权实体
    @ApiModelProperty(notes = "监控授权实体")
    private List<MonitorLicense> monitorLicense;

    //    通知授权实体
    @ApiModelProperty(notes = "通知授权实体")
    private List<WarningMsgLicense> warningMsgLicense;


}
