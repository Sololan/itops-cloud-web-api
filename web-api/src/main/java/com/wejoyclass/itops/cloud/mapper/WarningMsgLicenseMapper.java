package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.dto.MonitorWarningQueryParam;
import com.wejoyclass.itops.cloud.dto.WarningMsgLicenseDto;
import com.wejoyclass.itops.cloud.entity.MonitorLicense;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 18:38
 **/
@Mapper
public interface WarningMsgLicenseMapper extends CURDMapper<WarningMsgLicense, Long> {
    /*
     * @param 组织id
     * @return WarningMsgLicense实体
     * */
    WarningMsgLicense getBefore(Long orgId);

    /*
     * @param 查询参数
     * @return WarningMsgLicenseDto实体list
     * */
    List<WarningMsgLicenseDto> findWarningMsgList(MonitorWarningQueryParam params);

    /*
     * @param 组织id
     * @return WarningLicenseCode
     * */
    String findCodeByOrgId(Long orgId);

    Long findCodeByCode(String licenseCode);

    Long findOrgIdByCode(String code);

    /*
     * @param orgId
     * @return WarningMsgLicense实体列表
     * */
    List<WarningMsgLicense> findEntityList(Long orgId);

    /*
     * @param orgId
     * @return WarningMsgLicense实体列表(昨天添加)
     * */
    List<WarningMsgLicense> findYesterdayCode(Long orgId);

    /*
     * @param orgId
     * @return WarningMsgLicense实体列表(当前生效)
     * */
     WarningMsgLicense findEntityByOrgId(Long orgId);
}
