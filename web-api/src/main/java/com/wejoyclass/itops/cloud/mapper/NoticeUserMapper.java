package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.entity.NoticeUser;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 通知人 Mapper
* @author shixs
* date 2020-1-14
*/
@Mapper
public interface NoticeUserMapper extends CURDMapper<NoticeUser, Long> {


    /**
     * 根据通知规则id查询通知人list
     * @param noticeRuleId
     * @return
     */
    List<NoticeUser> getNoticeUserByNoticeRuleId(Long noticeRuleId);

    /**
     * 根据通知规则id删除通知人
     * @param noticeRuleId
     */
    void deleteNoticeUserByRuleId(Long noticeRuleId);
}
