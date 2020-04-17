package com.wejoyclass.itops.cloud.config;

import com.wejoyclass.core.util.DefaultLifecycle;
import com.wejoyclass.core.util.MapperUtil;
import com.wejoyclass.core.util.RedisUtil;
import com.wejoyclass.itops.cloud.dto.MessageContentDto;
import com.wejoyclass.itops.cloud.feign.MessageService;
import com.wejoyclass.itops.cloud.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {


    @Autowired
    MessageService messageService;
    @Autowired
    WarningService warningService;

    /**
     * 定义Spring容器加载完成后执行的任务
     */
    @Bean
    public DefaultLifecycle defaultLifecycle() {
        return new DefaultLifecycle(this::subscribe);
    }

    private void subscribe() {
        RedisUtil.subscribeExpired((message, pattern) -> {
            try{
                MessageContentDto messageContentDto =  MapperUtil.converToObject(message.toString(), MessageContentDto.class);
                if ("0".equals(warningService.get(messageContentDto.getId()).getWarningStatus())) {
                    messageService.putMessage(messageContentDto.getMessageEntityList());
                }
                System.out.println(message);
            }catch (Exception ignored){

            }
        });
    }
}
