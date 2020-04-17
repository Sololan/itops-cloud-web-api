package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.uc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class WeChatMapperTest {

    @Autowired
    WechatMapper weChatMapper;

    @Test
    void getUnboundUsers() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(2041L);
        userIds.add(2050L);
        userIds.add(2047L);
        userIds.add(2048L);
        userIds.add(2049L);
        Long orgId = 78L;
        List<User> users = weChatMapper.getUnboundUsers(userIds, orgId);
        for (User user : users) {
            log.info("id: {}, name: {}", user.getId(), user.getFullName());
        }
    }
}