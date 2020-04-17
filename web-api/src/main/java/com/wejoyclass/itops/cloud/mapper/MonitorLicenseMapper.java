package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.dto.MonitorLicenseDto;
import com.wejoyclass.itops.cloud.dto.MonitorWarningQueryParam;
import com.wejoyclass.itops.cloud.entity.MonitorLicense;
import com.wejoyclass.service.mapper.CURDMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/8 14:52
 **/
@Mapper
public interface MonitorLicenseMapper extends CURDMapper<MonitorLicense, Long> {

    /*
     * @param 组织id
     * @return MonitorLicenses实体
     * */
    MonitorLicense getBefore(Long orgId);


    /*
     * @param 组织id
     * @return 授权码LicenseCode
     * */
    String findCodeByOrgId(Long orgId);

    /*
     * @param 授权码
     * @return code的个数
     * */
    Long findCodeByCode(String licenseCode);

    /*
     * @param 授权码
     * @return MonitorLicense
     * */
    MonitorLicense findEntityByCode(String code);

    /*
     * @param 查询参数
     * @return MonitorLicenseDto实体列表
     * */
    List<MonitorLicenseDto> findList(MonitorWarningQueryParam monitorWarningQueryParam);

    /*
     * @param orgId
     * @return MonitorLicense实体列表
     * */
    List<MonitorLicense> findEntityList(Long orgId);
    /*
     * @param orgId
     * @return MonitorLicense实体列表(昨天添加)
     * */
    List<MonitorLicense> findYesterdayCode(Long orgId);
}
