package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.service.service.CURDService;
import com.wejoyclass.itops.cloud.dto.OrgDto;
import com.wejoyclass.itops.cloud.dto.OrgQueryParam;
import com.wejoyclass.itops.cloud.dto.TenantOrgDto;
import com.wejoyclass.uc.entity.Org;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/7 16:40
 **/
public interface TenantService extends CURDService<OrgDto, Long> {

    /*
     * 按条件分页查询
     * */
    Page<OrgDto> findPage(QueryParameter queryParameter, OrgQueryParam orgQueryParam);

    /**
     * 插入租户组织相关信息
     */
    void insertTenantOrg(TenantOrgDto tenantOrgDto);
    /**
     * 根据租户名获取实体
     */
    Org getByOrgName(String OrgFullName);
}
