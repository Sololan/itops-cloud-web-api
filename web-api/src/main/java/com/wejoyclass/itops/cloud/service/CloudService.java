package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.core.service.service.CURDService;
import com.wejoyclass.itops.cloud.dto.ActiveLicenseDto;
import com.wejoyclass.itops.cloud.dto.InitDataDto;
import com.wejoyclass.itops.cloud.dto.MonitorWarningDto;
import com.wejoyclass.itops.cloud.entity.*;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/14 8:50
 **/
public interface CloudService {
    ActiveLicenseDto verifyAuthorize(Long orgId);

    WarningMsgLicense connectStatus(MonitorLicense monitorLicense, Long orgId);

    Integer verifyLicenseCode(String code);

    InitDataDto initData(String code);

    void syncAddWarning(Warning warning);

    void syncUpdateWarning(Warning warning);
}
