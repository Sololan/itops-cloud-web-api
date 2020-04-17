package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.service.service.CURDService;
import com.wejoyclass.itops.cloud.dto.MonitorLicenseDto;
import com.wejoyclass.itops.cloud.dto.MonitorWarningQueryParam;
import com.wejoyclass.itops.cloud.entity.MonitorLicense;

import java.util.List;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 14:54
 **/
public interface MonitorLicenseService extends CURDService<MonitorLicense, Long> {
    /*
     * 保存MonitorLicense
     * */
    void insertMonitorLicense(MonitorLicenseDto monitorLicense);

    /*
     * 按条件分页查询
     * */
    Page<MonitorLicenseDto> findPage(QueryParameter queryParameter, MonitorWarningQueryParam monitorWarningQueryParam);

    /*
     * 按条件列表
     * */
    List<MonitorLicenseDto> findList(MonitorWarningQueryParam monitorWarningQueryParam);
}
