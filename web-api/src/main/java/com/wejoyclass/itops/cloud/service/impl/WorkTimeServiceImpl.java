package com.wejoyclass.itops.cloud.service.impl;

import com.wejoyclass.itops.cloud.entity.WorkTime;
import com.wejoyclass.itops.cloud.mapper.WorkTimeMapper;
import com.wejoyclass.itops.cloud.service.WorkTimeService;
import com.wejoyclass.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设置工作时间service
 */
@Service
@Transactional
public class WorkTimeServiceImpl extends BaseCURDServiceImpl<WorkTime, Long> implements WorkTimeService {

    @Autowired
    private WorkTimeMapper workTimeMapper;

    @Override
    public WorkTime getWorkTimeByOrgId(Long orgId) {
        return workTimeMapper.getNewWorkTimeByOrgId(orgId);
    }
}
