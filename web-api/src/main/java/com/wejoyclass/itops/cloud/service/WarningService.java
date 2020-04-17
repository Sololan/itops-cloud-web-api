package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.service.service.CURDService;
import com.wejoyclass.itops.cloud.dto.*;
import com.wejoyclass.itops.cloud.entity.Warning;
import com.wejoyclass.itops.cloud.entity.WarningStatistic;

import java.util.Date;
import java.util.List;

/**
 * @Author lixu
 * @Date 2020/1/9 18:59
 * @Version 1.0
 */

public interface WarningService extends CURDService<Warning, Long> {

    /*首页*/

    //查询某时间段内告警统计表数据
    public List<WarningStatisticDto> getWarningStatisticSInPeriodTime(WarningParameterDto warningParameterDto);

    //统计某时间段内已关闭的告警数量
    public List<WarningStatusCountDto> getWarningNumInPeriodTimeByClosed(WarningParameterDto warningParameterDto);

    //按告警级别统计今天告警数量
    public TodayWarningStatisticDto getWarningStatisticInToday(Long orgId);

    //统计某时间段内告警内容数量最多的TopN
    public List<WarningInfoDto> getTopNByWarningInfoCountInPeriodTime(WarningParameterDto warningParameterDto);


    /*告警查看*/

    //分页查询在Controller层实现
    public Page<Warning> findWarningPage(QueryParameter queryParameter, WarningQueryParam warningQueryParam);

    //根据告警id查询告警详细信息
    public Warning getWarningById(Long warningId);

    public void saveWarning(Warning warning);

    public void updateWarningByCode(Warning newWarning);

    Warning getByWarningCode(String warningCode);
}
