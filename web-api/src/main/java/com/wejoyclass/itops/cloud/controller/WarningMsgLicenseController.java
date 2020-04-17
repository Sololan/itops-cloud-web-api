package com.wejoyclass.itops.cloud.controller;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.params.Request;
import com.wejoyclass.core.util.CtrlUtil;
import com.wejoyclass.core.util.IdentifyUtil;
import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.MonitorWarningQueryParam;
import com.wejoyclass.itops.cloud.dto.WarningMsgLicenseDto;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;
import com.wejoyclass.itops.cloud.service.WarningMsgLicenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 17:24
 **/
@RestController
@Api(tags = "通知授权相关接口")
@RequestMapping("/warningMsgLicense")
public class WarningMsgLicenseController {

    @Autowired
    private WarningMsgLicenseService warningMsgLicenseService;

    @ApiOperation("监控授权分页信息")
    @PostMapping("/warningMsgLicense/page")
    public RespEntity<Page<WarningMsgLicenseDto>> getWarningMsgLicensePage(@RequestBody Request<MonitorWarningQueryParam> request) {
        return CtrlUtil.exe(result -> result.setVal(warningMsgLicenseService.findWarningMsgPage(request.getQueryParameter(), request.getQuery())));
    }

    @ApiOperation("添加监控授权")
    @PostMapping("/monitorLicenses")
    public RespEntity addWarningMsgLicense(@RequestBody WarningMsgLicenseDto warningMsgLicense) {
        return CtrlUtil.exe(result -> {
            warningMsgLicense.setLicenseCode(IdentifyUtil.uuid());
            warningMsgLicenseService.insertWarningMsgLicense(warningMsgLicense);
        });
    }
}
