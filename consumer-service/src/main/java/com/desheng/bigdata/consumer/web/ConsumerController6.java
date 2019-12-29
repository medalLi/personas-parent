package com.desheng.bigdata.consumer.web;

import com.desheng.bigdata.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 完成线程隔离 服务降级的熔断操作
 * 统一的管理
 */
@RestController
@RequestMapping("consumer6")
@DefaultProperties(defaultFallback = "fallbackMethod")
public class ConsumerController6 {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(ConsumerController6.class);
    @RequestMapping("{id}")
    @HystrixCommand//开启熔断
    public String queryById(@PathVariable("id") Long id) {
        long begin = System.currentTimeMillis();
        String uri = "http://user-service/user/" + id;
        String user = restTemplate.getForObject(uri, String.class);
        long end = System.currentTimeMillis();
        // 记录访问用时：
        logger.info("访问用时：{}, {}", end - begin, "哈哈哈");
        return user;
    }

    public String fallbackMethod() {
        return "服务器hin忙~";
    }
}
