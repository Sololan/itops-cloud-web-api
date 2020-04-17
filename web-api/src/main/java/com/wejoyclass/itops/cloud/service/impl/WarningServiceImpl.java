package com.wejoyclass.itops.cloud.service.impl;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.util.MapperUtil;
import com.wejoyclass.core.util.PageUtil;
import com.wejoyclass.core.util.StringUtil;
import com.wejoyclass.itops.cloud.dto.*;
import com.wejoyclass.itops.cloud.entity.Warning;
import com.wejoyclass.itops.cloud.entity.WarningStatistic;
import com.wejoyclass.itops.cloud.mapper.WarningMapper;
import com.wejoyclass.itops.cloud.service.WarningService;
import com.wejoyclass.service.impl.BaseCURDServiceImpl;
import com.wejoyclass.service.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lixu
 * @Date 2020/1/9 18:59
 * @Version 1.0
 */

@Service
@Transactional
public class WarningServiceImpl extends BaseCURDServiceImpl<Warning,Long> implements WarningService {

    @Resource
    WarningMapper warningMapper;

    @Override
    public List<WarningStatisticDto> getWarningStatisticSInPeriodTime(WarningParameterDto warningParameterDto) {
        return warningMapper.getWarningStatisticSInPeriodTime(warningParameterDto);
    }

    @Override
    public List<WarningStatusCountDto> getWarningNumInPeriodTimeByClosed(WarningParameterDto warningParameterDto) {
        return warningMapper.getWarningNumInPeriodTimeByClosed(warningParameterDto);
    }

    @Override
    public TodayWarningStatisticDto getWarningStatisticInToday(Long orgId) {
        return warningMapper.getWarningStatisticInToday(orgId);
    }

    @Override
    public List<WarningInfoDto> getTopNByWarningInfoCountInPeriodTime(WarningParameterDto warningParameterDto) {
        return warningMapper.getTopNByWarningInfoCountInPeriodTime(warningParameterDto);
    }

    @Override
    public Warning getWarningById(Long warningId) {
        return warningMapper.getWarningById(warningId);
    }

    @Override
    public Page<Warning> findWarningPage(QueryParameter queryParameter, WarningQueryParam warningQueryParam){

        StringUtil.encodeSqlLike(warningQueryParam,"ipAddress");
        StringUtil.encodeSqlLike(warningQueryParam,"warningInfo");

       return PageUtil.process(queryParameter,() -> warningMapper.findWarningList(warningQueryParam));
    }

    @Override
    public void saveWarning(Warning warning) {
        try{
            this.insert(warning);
        }catch (Exception ex){
            String errMsg = "Could not create warning";
            throw new IllegalStateException(errMsg, ex);
        }
    }

    @Override
    public void updateWarningByCode(Warning newWarning) {
        Warning exitingWarning = this.warningMapper.getByWarningCode(newWarning.getWarningCode());
        if(exitingWarning == null){
            throw new IllegalStateException(String.format("Warning with code %s not found", newWarning.getWarningCode()));
        }

        MapperUtil.copy(newWarning, exitingWarning);

        try {
            this.update(exitingWarning);
        } catch (Exception ex) {
            String errMsg = "Could not update the warning";
            throw new IllegalStateException(errMsg, ex);
        }

    }

    @Override
    public Warning getByWarningCode(String warningCode) {
        return warningMapper.getByWarningCode(warningCode);
    }
}

