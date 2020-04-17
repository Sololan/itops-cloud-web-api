package com.wejoyclass.itops.cloud.feign;

import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.DictGroupDto;
import com.wejoyclass.itops.cloud.entity.MessageEntity;
import com.wejoyclass.uc.entity.Org;
import com.wejoyclass.uc.entity.Role;
import com.wejoyclass.uc.entity.User;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:Zz
 * @Description:
 * @Data Created in: 17:45 2020/3/4
 * @Modified By:
 **/
@FeignClient(value = "web-message", configuration = {UcService.MultipartSupportConfig.class})
public interface MessageService {

    @PostMapping("/receive")
    RespEntity putMessage(@RequestBody List<MessageEntity> messageEntities);

}
