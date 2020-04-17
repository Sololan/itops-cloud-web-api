package com.wejoyclass.itops.cloud.controller;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.params.Request;
import com.wejoyclass.core.util.CtrlUtil;
import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.UserDto;
import com.wejoyclass.itops.cloud.service.TenantManageService;
import com.wejoyclass.uc.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 10:01
 **/
@RestController
@Api(tags = "租户管理员相关接口")
@RequestMapping("/tenantManage")
public class TenantManageController {

    @Autowired
    private TenantManageService tenantManageService;

    @ApiOperation("租户管理员信息分页")
    @PostMapping("/TenantManagePage")
    public RespEntity<Page<User>> getTenantManagePage(@RequestBody Request<Map<String,Object>> request){
        return CtrlUtil.exe(result -> {
            Page<User> userPage = tenantManageService.findPage(request.getQueryParameter(),request.getQuery());
            result.setVal(userPage);
        });
    }

    @ApiOperation("获取租户管理员信息")
    @GetMapping("/getTenantManage")
    public RespEntity<User> getTenantManagePage(@RequestParam("userId") Long id){
        return CtrlUtil.exe(result -> {
            UserDto userDto = tenantManageService.getTenantManage(id);
            result.setVal(userDto);
        });
    }

    @ApiOperation("保存租户管理员信息")
    @PutMapping("/addTenantManage")
    public RespEntity<User> addTenantManage(@RequestBody UserDto userDto){
        return CtrlUtil.exe(result -> {
            result.setVal(tenantManageService.addTenantManage(userDto));
        });
    }

}
