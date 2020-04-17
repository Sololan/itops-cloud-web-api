package com.wejoyclass.itops.cloud.service.impl;

import com.wejoyclass.itops.cloud.dto.UserDto;
import com.wejoyclass.itops.cloud.mapper.UserMapper;
import com.wejoyclass.itops.cloud.service.TenantManageService;
import com.wejoyclass.service.impl.BaseCURDServiceImpl;
import com.wejoyclass.uc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 11:41
 **/
@Service
@Transactional
public class TenantManageServiceImpl extends BaseCURDServiceImpl<User, Long> implements TenantManageService {

    @Autowired
    private UserMapper userMapper;

    public UserDto getTenantManage(Long id) {
        return userMapper.getUserInfo(id);
    }

    public UserDto addTenantManage(UserDto userDto) {
        User user = userDto;
        return new UserDto();
    }
}
