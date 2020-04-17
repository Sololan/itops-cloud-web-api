package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.core.service.service.CURDService;
import com.wejoyclass.itops.cloud.entity.WorkTime;

public interface WorkTimeService extends CURDService<WorkTime, Long> {

    /**
     * 根据组织id获取最新设置工作时间
     * @param orgId
     * @return
     */
    WorkTime getWorkTimeByOrgId(Long orgId);



}
