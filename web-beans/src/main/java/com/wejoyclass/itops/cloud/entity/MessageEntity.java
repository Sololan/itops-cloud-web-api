package com.wejoyclass.itops.cloud.entity;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 17:04 2020/3/3
 * @Modified By:
 **/
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageEntity {
    // 消息类型
    private Integer type;
    // 消息内容
    private String content;
    // 消息发送人
    private String to;

    private Date date;
}