package com.desheng.bigdata.consumer.web;

import com.desheng.bigdata.consumer.pojo.User;
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
 */
@RestController
@RequestMapping("consumer5")
public class ConsumerController5 {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(ConsumerController5.class);
    @RequestMapping("{id}")
    @HystrixCommand(fallbackMethod = "fallbackQueryById")
    public User queryById(@PathVariable("id") Long id) {
        long begin = System.currentTimeMillis();
        String uri = "http://user-service/user/" + id;
        User user = restTemplate.getForObject(uri, User.class);
        long end = System.currentTimeMillis();
        // 记录访问用时：
        logger.info("访问用时：{}, {}", end - begin, "哈哈哈");
        return user;
    }

    public User fallbackQueryById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Unknown");
        user.setUserName("来自火星的你，碰到了来自金星的她~");
        return user;
    }
}
