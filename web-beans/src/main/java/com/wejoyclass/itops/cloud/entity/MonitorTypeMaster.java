package com.wejoyclass.itops.cloud.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 20:25 2020/2/18
 * @Modified By:
 **/
@Getter
@Setter
public class MonitorTypeMaster {
    private Long id;
    private String monitorTypeName;
    private String monitorTypeCode;
    private Long sortNum;
}
