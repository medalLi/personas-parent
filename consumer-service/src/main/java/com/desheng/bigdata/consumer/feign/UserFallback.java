package com.desheng.bigdata.consumer.feign;

import com.desheng.bigdata.consumer.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class UserFallback implements UserFeignClient {
    @Override
    public String queryById(Long id) {
        try {
            User user = new User();
            user.setId(id);
            user.setName("未知用户");
            user.setUserName("ok~");
            ObjectMapper om = new ObjectMapper();
            return om.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "服务器hin忙！";
    }
}
