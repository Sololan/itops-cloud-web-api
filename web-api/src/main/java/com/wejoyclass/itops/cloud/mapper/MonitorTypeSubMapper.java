package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.entity.MonitorTypeSub;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 20:57 2020/2/18
 * @Modified By:
 **/
@Mapper
public interface MonitorTypeSubMapper extends CURDMapper<MonitorTypeSub, Long> {
    List<MonitorTypeSub> findAll();
}
