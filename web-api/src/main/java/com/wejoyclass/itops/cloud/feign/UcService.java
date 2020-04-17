package com.wejoyclass.itops.cloud.feign;

import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.DictGroupDto;
import com.wejoyclass.uc.entity.*;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Desc
 * @Author zhuzhao
 * @CreateTime 2020/1/14 13:06
 **/
@FeignClient(value = "uc", configuration = {UcService.MultipartSupportConfig.class})
public interface UcService {


    @GetMapping("/users/orgs/{orgId}/list")
    RespEntity<List<User>> getUserByOrgId(@PathVariable("orgId") Long orgId);

    @GetMapping("/orgs/{id}")
    RespEntity<Org> get(@PathVariable("id") Long id);

    @GetMapping("/dicts/groups/all")
    RespEntity<List<DictGroupDto>> getDictsGroups(@RequestParam("orgId") Long id);

    @GetMapping("/roles/orgs/{orgId}")
    RespEntity<List<Role>> getRoles(@PathVariable("orgId") Long id);
    /**
     * 引用配置类MultipartSupportConfig.并且实例化
     */
    @Configuration
    class MultipartSupportConfig {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        @Primary
        @Scope("prototype")
        public Encoder feignFormEncoder() {

            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }
}
