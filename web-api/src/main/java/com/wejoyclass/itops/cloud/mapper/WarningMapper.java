package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.dto.*;
import com.wejoyclass.itops.cloud.entity.Warning;
import com.wejoyclass.itops.cloud.entity.WarningStatistic;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @Author lixu
 * @Date 2020/1/9 15:45
 * @Version 1.0
 */

@Mapper
public interface WarningMapper extends CURDMapper<Warning,Long> {

    String TABLE_NAME = "t_ops_warning";

    /*首页*/

    //查询某时间段内告警统计表数据
    public List<WarningStatisticDto> getWarningStatisticSInPeriodTime(WarningParameterDto warningParameterDto);

    //统计某时间段内已关闭的告警数量
    public List<WarningStatusCountDto> getWarningNumInPeriodTimeByClosed(WarningParameterDto warningParameterDto);

    //统计今天告警统计信息
    public TodayWarningStatisticDto getWarningStatisticInToday(Long orgId);

    //统计某时间段内告警内容数量最多的TopN
    public List<WarningInfoDto> getTopNByWarningInfoCountInPeriodTime(WarningParameterDto warningParameterDto);


    /*告警查看*/

    //分页查询在
    List<Warning> findWarningList(WarningQueryParam warningQueryParam);

    //根据告警id查询告警详细信息
    public Warning getWarningById(Long warningId);

    @Select({"select * from", TABLE_NAME, "where WARNING_CODE = #{warningCode} and DELETE_FLAG = 0"})
    public Warning getByWarningCode(String warningCode);
}
