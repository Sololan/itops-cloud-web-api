package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.core.service.service.CURDService;
import com.wejoyclass.itops.cloud.dto.UserDto;
import com.wejoyclass.uc.entity.User;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 11:40
 **/
public interface TenantManageService extends CURDService<User, Long> {

    UserDto getTenantManage(Long id);
    User addTenantManage(UserDto userDto);
}
