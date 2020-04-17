package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.dto.OrgDto;
import com.wejoyclass.itops.cloud.dto.OrgQueryParam;
import com.wejoyclass.itops.cloud.dto.TenantOrgDto;
import com.wejoyclass.service.mapper.CURDMapper;
import com.wejoyclass.uc.entity.Org;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/7 17:00
 **/
@Mapper
public interface OrgMapper extends CURDMapper<OrgDto, Long> {

    /*
     * @param 查询条件
     * @return OrgDto实体
     * */
    List<OrgDto> findList(OrgQueryParam orgQueryParam);

    /**
     * 插入Org信息
     * @param tenantOrgDto
     * @return  返回org的id
     */
    Long insertOrg(TenantOrgDto tenantOrgDto);

    /**
     * 插入role信息
     * @param tenantOrgDto
     * @return 返回role的id
     */
    Long insertRole(TenantOrgDto tenantOrgDto);

    /**
     * 查询角色需要的资源id[根据code查id](t_base_resource)
     * @param resourceCode
     * @return
     */
    Long getResourceIdByResourceCode(String resourceCode);

    /**
     * 将角色-资源关系插入[角色资源表](t_base_role_resource)
     * @param roleId
     * @param resourceId
     */
    void insertRoleResource(Long roleId, Long resourceId);

    /**
     * 根据用户名获取实体
     * @param OrgFullName
     * @param
     */
    Org getByOrgName(String orgFullName);

}
