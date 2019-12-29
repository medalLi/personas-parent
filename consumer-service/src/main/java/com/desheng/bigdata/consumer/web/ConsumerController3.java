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
 */
@RestController
@RequestMapping("consumer3")
public class ConsumerController3 {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient client;

    @RequestMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        //基于ribbon提供的负载均衡器来实现了请求的loadbalance
        ServiceInstance instance = client.choose("user-service");
        String host = instance.getHost();
        int port = instance.getPort();
        String uri = "http://" + host + ":" + port + "/user/" + id;
        System.out.println("uri---->" + uri);
        User user = restTemplate.getForObject(uri, User.class);
        return user;
    }
}
