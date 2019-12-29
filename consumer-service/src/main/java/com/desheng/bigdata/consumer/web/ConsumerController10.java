package com.desheng.bigdata.consumer.web;

import com.desheng.bigdata.consumer.feign.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局，针对所有的controller都可以使用的熔断之后的回调
 * 注意：如果开启了feign的熔断机制之后，在controller中就不要在使用hystrix的熔断注解
 * 不然二者会有冲突
 */
@RestController
@RequestMapping("consumer10")
public class ConsumerController10 {

    @Autowired
    private UserFeignClient client;

    @RequestMapping("{id}")
    public String queryById(@PathVariable("id") Long id) {
        return client.queryById(id);
    }
}
