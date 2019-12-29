package com.desheng.bigdata.consumer.web;

import com.desheng.bigdata.consumer.feign.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * feign的使用
 */
@RestController
@RequestMapping("consumer9")
@DefaultProperties(defaultFallback = "fallbackMethod")
public class ConsumerController9 {

    @Autowired
    private UserFeignClient client;

    @RequestMapping("{id}")
    @HystrixCommand
    public String queryById(@PathVariable("id") Long id) {
        if(id % 2 == 0) {
            throw new RuntimeException();
        }
        return client.queryById(id);
    }

    public String fallbackMethod() {
        return "服务器hin忙~";
    }
}
