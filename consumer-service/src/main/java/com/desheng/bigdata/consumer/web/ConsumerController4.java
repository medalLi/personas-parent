package com.desheng.bigdata.consumer.web;

import com.desheng.bigdata.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 完成多个服务提供方的选择，也就是负载均衡
 *  得需要基于ribbon来进行实现
 *  常见的负载均衡策略：
 *      随机
 *      轮询
 *      hash(ip进行hash)
 *
 *  终极版的负载均衡写法
 */
@RestController
@RequestMapping("consumer4")
public class ConsumerController4 {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        String uri = "http://user-service/user/" + id;
        User user = restTemplate.getForObject(uri, User.class);
        return user;
    }
}
