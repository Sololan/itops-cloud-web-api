package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.entity.Connect;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/14 10:41
 **/
@Mapper
public interface ConnectMapper extends CURDMapper<Connect, Long> {
    /*根据orgId判断是否存在*/
    Long getByOrgId(Long orgId);
    void updateById(Connect connect);
}

