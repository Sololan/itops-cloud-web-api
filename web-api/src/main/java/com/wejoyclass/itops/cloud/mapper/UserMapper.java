package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.dto.UserDto;
import com.wejoyclass.service.mapper.CURDMapper;
import com.wejoyclass.uc.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 10:23
 **/
@Mapper
public interface UserMapper extends CURDMapper<User, Long> {
    UserDto getUserInfo(Long id);
}
