package com.wejoyclass.itops.cloud.mapper;

import com.wejoyclass.itops.cloud.dto.UserIdOrdIdDto;
import com.wejoyclass.uc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WechatMapper {
    // 绑定
    void bind(Long orgId, Long userId, String openId);

    // 解绑
    void unbind(Long id);

    // 是否存在
    Integer isBind(Long id);

    // 获取orgId
    UserIdOrdIdDto getUserIdOrgId(String username);

    /**
     * 查询userId中没绑定微信的用户
     *
     * @param userIds
     * @param orgId
     * @return
     */
    List<User> getUnboundUsers(List<Long> userIds, Long orgId);
}
