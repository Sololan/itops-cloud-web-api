package com.wejoyclass.itops.cloud.service;

import com.wejoyclass.itops.cloud.dto.WechatBindDto;

public interface WechatService {
    // 绑定
    void bind(WechatBindDto wechatBindDto);
    // 解绑
    void unbind(Long id);
    // 是否存在
    Boolean isBind(Long id);
}
