package com.wejoyclass.itops.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.wejoyclass.core.util.CtrlUtil;
import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.WechatBindDto;
import com.wejoyclass.itops.cloud.dto.WxRespDto;
import com.wejoyclass.itops.cloud.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc
 * @Author liuzt
 * @CreateTime 2020/3/5 17:24
 **/
@RestController
@Api(tags = "微信相关接口")
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    WechatService wechatService;

    @ApiOperation("微信绑定")
    @PostMapping("/bind")
    public RespEntity bind(@RequestBody WechatBindDto wechatBindDto){
        return CtrlUtil.exe(r -> {
            wechatService.bind(wechatBindDto);
        });
    }

    @ApiOperation("微信解绑")
    @PostMapping("/unbind/{id}")
    public RespEntity unbind(@PathVariable("id") Long id){
        return CtrlUtil.exe(r -> {
            wechatService.unbind(id);
        });
    }

    @ApiOperation("是否绑定了微信")
    @GetMapping("/isBind/{id}")
    public RespEntity<Boolean> isBind(@PathVariable("id") Long id){
        return CtrlUtil.exe(r -> r.setVal(wechatService.isBind(id)));
    }
}
