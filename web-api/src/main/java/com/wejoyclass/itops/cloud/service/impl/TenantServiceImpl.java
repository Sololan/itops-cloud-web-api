package com.wejoyclass.itops.cloud.service.impl;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.util.ExceptionUtil;
import com.wejoyclass.core.util.MapperUtil;
import com.wejoyclass.core.util.PageUtil;
import com.wejoyclass.itops.cloud.dto.OrgDto;
import com.wejoyclass.itops.cloud.dto.OrgQueryParam;
import com.wejoyclass.itops.cloud.dto.TenantOrgDto;
import com.wejoyclass.itops.cloud.mapper.OrgMapper;
import com.wejoyclass.itops.cloud.service.TenantService;
import com.wejoyclass.service.impl.BaseCURDServiceImpl;
import com.wejoyclass.service.util.EntityUtil;
import com.wejoyclass.uc.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/7 16:40
 **/
@Service
@Transactional
public class TenantServiceImpl extends BaseCURDServiceImpl<OrgDto, Long> implements TenantService {

    @Autowired
    private OrgMapper orgMapper;

    public Page<OrgDto> findPage(QueryParameter queryParameter, OrgQueryParam orgQueryParam) {
        return PageUtil.process(queryParameter, () -> {
            return orgMapper.findList(orgQueryParam);
        });
    }

    public void insertTenantOrg(TenantOrgDto tenantOrgDto){

        if(getByOrgName(tenantOrgDto.getFullName()) != null){
            throw ExceptionUtil.msg(1,"租户名已存在");
        }

        tenantOrgDto.setStatus(1);

        //  插入租户信息，并获取租户id
        tenantOrgDto.setDate(new Date());
        orgMapper.insertOrg(tenantOrgDto);
        Long orgId = tenantOrgDto.getId();

        //  设置组织id为租户id
        tenantOrgDto.setOrgId(orgId.toString());

        //  初始化角色1：租户管理员[插入角色表]
        tenantOrgDto.setRname("租户管理员");
        tenantOrgDto.setRcode("ROLE_MANAGER");
        tenantOrgDto.setRenable(1);
        tenantOrgDto.setRsort("1");
        orgMapper.insertRole(tenantOrgDto);
        Long tenantManagerRoleId = tenantOrgDto.getRid();


        // 初始化角色2: 普通用户[插入角色表]
        tenantOrgDto.setRname("普通用户");
        tenantOrgDto.setRcode("ROLE_USER");
        orgMapper.insertRole(tenantOrgDto);
        Long tenantUserRoleId = tenantOrgDto.getRid();

        // 根据需求，查询初始化角色所需要的资源id[根据资源code查询资源id](t_base_resource)

        // 查找租户管理员对应的资源id[首页、告警查看、通知规则设置、工作时间、用户管理、个人信息]
        HashMap<String, Long> mapTenantManager = new HashMap();
        mapTenantManager.put("homeId", orgMapper.getResourceIdByResourceCode("home"));
        mapTenantManager.put("warningId", orgMapper.getResourceIdByResourceCode("warning"));
        mapTenantManager.put("noticeRuleId", orgMapper.getResourceIdByResourceCode("noticerule"));
        mapTenantManager.put("worktimeId", orgMapper.getResourceIdByResourceCode("worktime"));
        mapTenantManager.put("userId", orgMapper.getResourceIdByResourceCode("user"));
        mapTenantManager.put("infoId", orgMapper.getResourceIdByResourceCode("info"));

        // 查找普通用户对应的资源id[首页、告警查看、个人信息]
        HashMap<String, Long> mapUser = new HashMap();
        mapUser.put("homeId", orgMapper.getResourceIdByResourceCode("home"));
        mapUser.put("warningId", orgMapper.getResourceIdByResourceCode("warning"));
        mapUser.put("infoId", orgMapper.getResourceIdByResourceCode("info"));

        // 将角色id和查到的资源id插入角色资源表(t_role_resource)
        // 插入租户管理员对应信息
        for (String key: mapTenantManager.keySet()) { // 遍历key
            orgMapper.insertRoleResource(tenantManagerRoleId, mapTenantManager.get(key));
        }
        // 插入普通用户对应信息
        for (String key: mapUser.keySet()) { // 遍历key
            orgMapper.insertRoleResource(tenantUserRoleId, mapUser.get(key));
        }

    }

    public Org getByOrgName(String orgFullName) {
        return orgMapper.getByOrgName(orgFullName);
    }

}
