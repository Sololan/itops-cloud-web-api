package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.entity.NoticeWay;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 通知方式 Mapper
* @author shixs
* date 2020-1-14
*/
@Mapper
public interface NoticeWayMapper extends CURDMapper<NoticeWay, Long> {

    /**
     * 根据通知规则id查询通知方式list
     * @param noticeRuleId
     * @return
     */
    List<NoticeWay> getNoticeWayByNoticeRuleId(Long noticeRuleId);

    /**
     * 根据通知规则删除通知方式
     * @param noticeRuleId
     */
    void deleteNoticeWayByRuleId(Long noticeRuleId);
}
