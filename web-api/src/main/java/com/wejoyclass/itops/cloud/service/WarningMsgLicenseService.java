package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.service.service.CURDService;
import com.wejoyclass.itops.cloud.dto.MonitorWarningQueryParam;
import com.wejoyclass.itops.cloud.dto.WarningMsgLicenseDto;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 18:39
 **/
public interface WarningMsgLicenseService extends CURDService<WarningMsgLicense, Long> {
    /*
     * 保存WarningMsgLicense
     * */
    void insertWarningMsgLicense(WarningMsgLicenseDto warningMsgLicenseDto);

    /*
     * 按条件分页查询
     * */
    Page<WarningMsgLicenseDto> findWarningMsgPage(QueryParameter queryParameter, MonitorWarningQueryParam monitorWarningQueryParam);
}
