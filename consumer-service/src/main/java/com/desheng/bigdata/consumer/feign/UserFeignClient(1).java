package com.desheng.bigdata.consumer.feign;

import com.desheng.bigdata.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-service", fallback = UserFallback.class)
public interface UserFeignClient {

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
//    @GetMapping//简写方式
    String queryById(@PathVariable("id") Long id);
}
