package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.dto.MessageUsedDto;
import com.wejoyclass.itops.cloud.dto.NoticeRuleQueryParam;
import com.wejoyclass.itops.cloud.entity.NoticeRule;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 通知规则 Mapper
* @author shixs
* date 2020-1-14
*/
@Mapper
public interface NoticeRuleMapper extends CURDMapper<NoticeRule, Long> {

    /**
     * 根据条件查询通知规则list
     * @param noticeRuleDto
     * @return
     */
     List<NoticeRuleQueryParam> findNoticeRuleList(NoticeRuleQueryParam noticeRuleDto);

    /**
     * 根据id查询通知规则
     * @param id
     * @return
     */
     NoticeRule getNoticeRuleById(Long id);

    /**
     * 根据告警级别查询id
     * @param warningLevel
     * @return
     */
     List<MessageUsedDto> getMessageUsedByWarningLevelOrgId(Long warningLevel, Long orgId);

}
