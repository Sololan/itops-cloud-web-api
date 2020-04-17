package com.wejoyclass.itops.cloud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/14 9:06
 **/
@Getter
@Setter
@Table(name="t_ops_active_code")
public class ActiveCode {

    //id
    Long id;

    //组织id
    Long orgId;
    
    //监控授权码
    String monitorActiveCode;

    //通知激活码
    String warningMsgActiveCode;
}
