package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.entity.WorkTime;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设置工作时间Mapper
 */
@Mapper
public interface WorkTimeMapper extends CURDMapper<WorkTime, Long> {


    /**
     * 根据组织id获取最新设置工作时间
     * @param orgId
     * @return
     */
    WorkTime getNewWorkTimeByOrgId(Long orgId);


    /**
     * 根据组织id获取最新设置工作时间(同步用)
     * @param orgId
     * @return
     */
    WorkTime getNewWorkTime(Long orgId);
}
