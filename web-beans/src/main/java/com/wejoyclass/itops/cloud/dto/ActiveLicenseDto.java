package com.wejoyclass.itops.cloud.dto;

import com.wejoyclass.itops.cloud.entity.MonitorLicense;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 21:26 2020/2/19
 * @Modified By:
 **/
@Getter
@Setter
public class ActiveLicenseDto {

    @ApiModelProperty(notes = "监控授权实体")
    private List<MonitorLicense> monitorLicense;

    @ApiModelProperty(notes = "告警授权实体")
    private List<WarningMsgLicense> warningMsgLicense;

}
