package com.wejoyclass.itops.cloud.service.impl;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.util.DateUtil;
import com.wejoyclass.core.util.MapperUtil;
import com.wejoyclass.core.util.PageUtil;
import com.wejoyclass.itops.cloud.dto.MonitorWarningQueryParam;
import com.wejoyclass.itops.cloud.dto.WarningMsgLicenseDto;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;
import com.wejoyclass.itops.cloud.mapper.WarningMsgLicenseMapper;
import com.wejoyclass.itops.cloud.service.WarningMsgLicenseService;
import com.wejoyclass.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 18:39
 **/

@Service
@Transactional
public class WarningMsgLicenseSericeImpl extends BaseCURDServiceImpl<WarningMsgLicense, Long> implements WarningMsgLicenseService {

    @Autowired
    private WarningMsgLicenseMapper warningMsgLicenseMapper;

    public void insertWarningMsgLicense(WarningMsgLicenseDto warningMsgLicenseDto) {

        WarningMsgLicense warningMsgLicense = new WarningMsgLicense();
        //todo 实体转换
        MapperUtil.copy(warningMsgLicenseDto, warningMsgLicense);
        warningMsgLicense.setExpireTime(DateUtil.addDays(warningMsgLicense.getEndDate(), 1));
        //        获取上一条授权信息
        WarningMsgLicense warningMsgLicenseOld = warningMsgLicenseMapper.getBefore(warningMsgLicense.getOrgId());
        if (warningMsgLicenseOld == null) {
            super.insert(warningMsgLicense);
        } else if (warningMsgLicenseOld.getExpireTime().after(warningMsgLicense.getStartDate())) {
            warningMsgLicenseOld.setExpireTime(DateUtil.addDays(warningMsgLicense.getStartDate(), 1));
            super.update(warningMsgLicenseOld);
            super.insert(warningMsgLicense);
        } else {
            super.insert(warningMsgLicense);
        }
    }

    public Page<WarningMsgLicenseDto> findWarningMsgPage(QueryParameter queryParameter, MonitorWarningQueryParam monitorWarningQueryParam) {
        return PageUtil.process(queryParameter, () -> warningMsgLicenseMapper.findWarningMsgList(monitorWarningQueryParam));
    }


}
