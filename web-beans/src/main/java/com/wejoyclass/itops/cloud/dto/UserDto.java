package com.wejoyclass.itops.cloud.dto;

import com.wejoyclass.uc.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 11:34
 **/
@Getter
@Setter
public class UserDto extends User {
    private Long orgId;
    private Long roleId;
    private String roleName;
}
