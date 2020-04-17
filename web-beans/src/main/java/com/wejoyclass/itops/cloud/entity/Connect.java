package com.wejoyclass.itops.cloud.entity;

import com.wejoyclass.core.service.entity.BaseMysqlEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/7 16:56
 **/
@Getter
@Setter
@Table(name = "t_ops_connect")
public class Connect {

    //id
    private Long id;

    //最后连接时间
    private Date lastConnectTime;

    //组织id
    private Long orgId;
}
