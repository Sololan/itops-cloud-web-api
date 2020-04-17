package com.wejoyclass.itops.cloud.controller;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.params.Request;
import com.wejoyclass.core.util.CtrlUtil;
import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.core.util.StringUtil;
import com.wejoyclass.itops.cloud.dto.OrgDto;
import com.wejoyclass.itops.cloud.dto.OrgQueryParam;
import com.wejoyclass.itops.cloud.dto.TenantOrgDto;
import com.wejoyclass.itops.cloud.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/7 16:31
 **/
@RestController
@Api(tags = "租户相关接口")
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @ApiOperation("租户信息分页")
    @PostMapping("/tenantPage")
    public RespEntity<Page<OrgDto>> getTenantPage(@RequestBody Request<OrgQueryParam> request) {
        return CtrlUtil.exe(result -> {
            StringUtil.encodeSqlLike(request.getQuery(), "fullName", "contact");
            result.setVal(tenantService.findPage(request.getQueryParameter(), request.getQuery()));
        });
    }

    @ApiOperation("插入租户组织信息")
    @PostMapping("/orgs")
    public RespEntity addOrgs(@RequestBody TenantOrgDto tenantOrgDto){
        return CtrlUtil.exe(result -> tenantService.insertTenantOrg(tenantOrgDto));
    }
}
