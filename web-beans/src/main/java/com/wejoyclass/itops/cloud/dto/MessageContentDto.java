package com.wejoyclass.itops.cloud.dto;

import com.wejoyclass.itops.cloud.entity.MessageEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 18:24 2020/3/3
 * @Modified By:
 **/
@Getter
@Setter
public class MessageContentDto {
    private List<MessageEntity> messageEntityList;
    private Long sendTime;
    private Long id;
}
