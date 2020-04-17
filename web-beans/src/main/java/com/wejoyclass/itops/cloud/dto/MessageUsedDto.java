package com.wejoyclass.itops.cloud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 22:33 2020/3/3
 * @Modified By:
 **/
@Getter
@Setter
public class MessageUsedDto {

    //告警规则id
    private Long id;

    // 告警级别（字典项）
    private Long warningLevel;

    // 告警阶段（字典项）
    private Long warningStage;

    // 通知时间（1：任何时间 2：非工作时间 3：工作时间，周一到周五8：30-17：30）
    private Integer noticeTiming;

    // 延迟策略（1：立刻 2：延迟5分钟 3：延迟10分钟）
    private Integer postponementStrategy;

    //通知人id
    private Long noticeUser;

    //通知人姓名
    private String fullName;

    //电话
    private String phone;

    //email
    private String email;

    //微信
    private String openId;

    //通知方式
    private Long noticeMethod;

    //告警阶段字典key
    private Long stageItemKey;

    //告警等级字典key
    private Long levelItemKey;

    ////告警等级字典Name
    private String levelName;

    //告警延时策略字典key
    private Long strategyItemKey;

    //告警方式字典key
    private Long methodItemKey;

}
