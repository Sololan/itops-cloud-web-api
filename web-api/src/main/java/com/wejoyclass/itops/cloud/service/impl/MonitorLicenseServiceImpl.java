package com.wejoyclass.itops.cloud.service.impl;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.util.DateUtil;
import com.wejoyclass.core.util.MapperUtil;
import com.wejoyclass.core.util.PageUtil;
import com.wejoyclass.itops.cloud.dto.MonitorLicenseDto;
import com.wejoyclass.itops.cloud.dto.MonitorWarningQueryParam;
import com.wejoyclass.itops.cloud.entity.MonitorLicense;
import com.wejoyclass.itops.cloud.mapper.MonitorLicenseMapper;
import com.wejoyclass.itops.cloud.service.MonitorLicenseService;
import com.wejoyclass.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 14:54
 **/

@Service
@Transactional
public class MonitorLicenseServiceImpl extends BaseCURDServiceImpl<MonitorLicense, Long> implements MonitorLicenseService {
    @Autowired
    private MonitorLicenseMapper monitorLicenseMapper;

    public void insertMonitorLicense(MonitorLicenseDto monitorLicenseDto) {

        MonitorLicense monitorLicense = new MonitorLicense();
        MapperUtil.copy(monitorLicenseDto, monitorLicense);
        //        获取最新的授权信息
        MonitorLicense monitorLicenseOld = monitorLicenseMapper.getBefore(monitorLicense.getOrgId());
        monitorLicense.setExpireTime(DateUtil.addDays(monitorLicense.getEndDate(), 1));
        if (monitorLicenseOld == null) {
            super.insert(monitorLicense);
        } else if (monitorLicenseOld.getExpireTime().after(monitorLicense.getStartDate())) {
            monitorLicenseOld.setExpireTime(DateUtil.addDays(monitorLicense.getStartDate(), 1));
            super.update(monitorLicenseOld);
            super.insert(monitorLicense);
        } else {
            super.insert(monitorLicense);
        }
    }

    public Page<MonitorLicenseDto> findPage(QueryParameter queryParameter, MonitorWarningQueryParam monitorWarningQueryParam) {
        return PageUtil.process(queryParameter, () -> this.findList(monitorWarningQueryParam));
    }

    public List<MonitorLicenseDto> findList(MonitorWarningQueryParam monitorWarningQueryParam) {
        return monitorLicenseMapper.findList(monitorWarningQueryParam);
    }


}
