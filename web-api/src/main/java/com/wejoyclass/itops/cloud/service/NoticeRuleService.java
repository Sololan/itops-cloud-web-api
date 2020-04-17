package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.service.service.CURDService;
import com.wejoyclass.itops.cloud.dto.NoticeRuleQueryParam;
import com.wejoyclass.itops.cloud.entity.NoticeRule;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;

import java.util.List;

public interface NoticeRuleService extends CURDService<NoticeRule, Long> {


    /**
     * 根据条件查询通知规则list
     * @param noticeRuleDto
     * @return
     */
     List<NoticeRuleQueryParam> findNoticeRuleList(NoticeRuleQueryParam noticeRuleDto);

    /**
     * 根据条件查询通知规则（分页）
     * @param queryParameter
     * @param noticeRuleDto
     * @return
     */
    Page<NoticeRuleQueryParam> findNoticeRulePagesByContidion(QueryParameter queryParameter, NoticeRuleQueryParam noticeRuleDto);

    /**
     * 根据id查询通知规则
     * @param id
     * @return
     */
    NoticeRule getNoticeRuleById(Long id);

    /**
     * 根据组织id查询告警消息平台授权实体
     * @param orgId
     * @return
     */
    WarningMsgLicense getNoticeWayByOrgId(Long orgId);



}
