package com.wejoyclass.itops.cloud.controller;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.params.Request;
import com.wejoyclass.core.util.CtrlUtil;
import com.wejoyclass.core.util.IdentifyUtil;
import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.MonitorLicenseDto;
import com.wejoyclass.itops.cloud.dto.MonitorWarningQueryParam;
import com.wejoyclass.itops.cloud.entity.MonitorLicense;
import com.wejoyclass.itops.cloud.service.MonitorLicenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 14:33
 **/
@RestController
@Api(tags = "监控授权相关接口")
public class MonitorLicenseController {

    @Autowired
    private MonitorLicenseService monitorLicenseService;

    @ApiOperation("监控授权分页信息")
    @PostMapping("/monitorLicenses/page")
    public RespEntity<Page<MonitorLicenseDto>> getMonitorLicensePage(@RequestBody Request<MonitorWarningQueryParam> request) {
        return CtrlUtil.exe(result -> result.setVal(monitorLicenseService.findPage(request.getQueryParameter(), request.getQuery())));
    }

    @ApiOperation("添加监控授权")
    @PostMapping("/monitorLicenses")
    public RespEntity insertMonitorLicense(@RequestBody MonitorLicenseDto monitorLicense) {
        monitorLicense.setLicenseCode(IdentifyUtil.uuid());
        return CtrlUtil.exe(result -> monitorLicenseService.insertMonitorLicense(monitorLicense));
    }
}
