package com.wejoyclass.itops.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wejoyclass.core.util.ExceptionUtil;
import com.wejoyclass.core.util.HttpUtil;
import com.wejoyclass.itops.cloud.dto.UserIdOrdIdDto;
import com.wejoyclass.itops.cloud.dto.WechatBindDto;
import com.wejoyclass.itops.cloud.dto.WxRespDto;
import com.wejoyclass.itops.cloud.mapper.WechatMapper;
import com.wejoyclass.itops.cloud.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WechatServiceImpl implements WechatService {
    @Autowired
    WechatMapper wechatMapper;

    @Value("${wx.appid}")
    String appid;

    @Value("${wx.secret}")
    String secret;

    @Override
    public void bind(WechatBindDto wechatBindDto) {
        // 获取此用户的orgId
        UserIdOrdIdDto us = wechatMapper.getUserIdOrgId(wechatBindDto.getUsername());
        // 没有则抛出异常
        if(us == null){
            throw ExceptionUtil.msg("不存在此用户，请重新输入");
        }
        // 根据code获取openid
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ appid +"&secret="+ secret +"&code="+ wechatBindDto.getCode() +"&grant_type=authorization_code";
        log.info("request url: " + url);
        String wxr = HttpUtil.get(url);
        log.info("wechat back msg: " + wxr);
        // 如果请求不到openid，则抛出异常
        WxRespDto wxRespDto = JSONObject.parseObject(wxr, WxRespDto.class);
        if(wxRespDto.getErrcode() != null){
            throw ExceptionUtil.msg(-1,wxRespDto.getErrmsg());
        }
        // unbind之前的
        unbind(us.getUserId());
        // bind现在的
        wechatMapper.bind(us.getOrgId(),us.getUserId(),wxRespDto.getOpenid());
    }

    @Override
    public void unbind(Long id) {
        wechatMapper.unbind(id);
    }

    @Override
    public Boolean isBind(Long id) {
        Boolean flag;
        Integer count = wechatMapper.isBind(id);
        flag = (count > 0)?true:false;
        return flag;
    }
}
