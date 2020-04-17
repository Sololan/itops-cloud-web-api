package com.wejoyclass.itops.cloud.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 20:18 2020/2/18
 * @Modified By:
 **/
@Getter
@Setter
public class MonitorTypeSub {

    private Long id;
    private Long equipmentSubType;
    private Long monitorTypeId;
    private String subName;
    private Long sortNum;
}
