package com.desheng.bigdata.consumer.web;

import com.desheng.bigdata.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer2")
public class ConsumerController2 {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        //instances 就是从注册中心获取的服务名称为user-service的服务列表
        List<ServiceInstance> instances = client.getInstances("user-service");
        ServiceInstance instance = instances.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String uri = "http://" + host + ":" + port + "/user/" + id;
        User user = restTemplate.getForObject(uri, User.class);
        return user;
    }
}
