package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.entity.MonitorSubMethod;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 20:56 2020/2/18
 * @Modified By:
 **/
@Mapper
public interface MonitorSubMethodMapper extends CURDMapper<MonitorSubMethod, Long> {
    List<MonitorSubMethod> findAll();

}
