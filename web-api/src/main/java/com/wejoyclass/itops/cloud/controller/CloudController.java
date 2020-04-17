package com.wejoyclass.itops.cloud.controller;

import com.wejoyclass.core.util.CtrlUtil;
import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.InitDataDto;
import com.wejoyclass.itops.cloud.entity.MonitorLicense;
import com.wejoyclass.itops.cloud.entity.Warning;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;
import com.wejoyclass.itops.cloud.mapper.NoticeRuleMapper;
import com.wejoyclass.itops.cloud.service.CloudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/13 20:03
 **/
@RestController
@Api(tags = "本地平台调用相关")
public class CloudController {

    @Autowired
    private CloudService cloudService;
    @Autowired
    private NoticeRuleMapper noticeRuleMapper;

    @ApiOperation("授权验证更新授权码")
    @GetMapping("/authorization/{orgId}")
    RespEntity verifyAuthorize(@PathVariable("orgId") Long orgId) {
        return CtrlUtil.exe(result -> result.setVal(cloudService.verifyAuthorize(orgId)));
    }

    @ApiOperation("检查连接状态/并同步信息监控使用状态/告警信息使用状态")
    @PostMapping("/connect/{orgId}")
    RespEntity<WarningMsgLicense> connectStatus(@RequestBody MonitorLicense monitorLicense, @PathVariable("orgId") Long orgId) {
        return CtrlUtil.exe(result -> result.setVal(cloudService.connectStatus(monitorLicense, orgId)));
    }

    @ApiOperation("验证授权码是否正确")
    @GetMapping("/verifyLicenseCode/{code}")
    RespEntity<Boolean> verifyLicenseCode(@PathVariable("code") String code) {
        return CtrlUtil.exe(result ->{
            Integer flag = cloudService.verifyLicenseCode(code);
            if(flag == 0){
                result.setCode(0);
            }else if(flag == 1){
                result.setCode(1);
                result.setMessage("授权码过期");
            }else if(flag == -1){
                result.setCode(-1);
                result.setMessage("授权码错误");
            }
        });
    }

    @ApiOperation("初始化数据")
    @GetMapping("/initData/{code}")
    RespEntity<InitDataDto> initData(@PathVariable("code") String code) {
        return CtrlUtil.exe(result -> result.setVal(cloudService.initData(code)));
    }

    @ApiOperation("同步本地告警信息并添加")
    @PostMapping("/warning/syncAdd")
    public RespEntity syncAddWarning(@RequestBody Warning warning){
        return CtrlUtil.exe(result -> cloudService.syncAddWarning(warning));
    }

    @ApiOperation("同步本地告警信息并修改")
    @PostMapping("/warning/syncUpdate")
    public RespEntity syncUpdateWarning(@RequestBody Warning warning){
        return CtrlUtil.exe(result -> cloudService.syncUpdateWarning(warning));
    }
}
