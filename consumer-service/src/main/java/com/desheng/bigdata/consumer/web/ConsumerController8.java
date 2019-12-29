package com.desheng.bigdata.consumer.web;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 服务的熔断机制
 */
@RestController
@RequestMapping("consumer8")
@DefaultProperties(defaultFallback = "fallbackMethod")
public class ConsumerController8 {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(ConsumerController8.class);
    @RequestMapping("{id}")
    //开启熔断
    @HystrixCommand(
        commandProperties = {
            @HystrixProperty(
                name="circuitBreaker.requestVolumeThreshold", value="10"
            ),
            @HystrixProperty(
                name="circuitBreaker.sleepWindowInMilliseconds", value="10000"
            ),
            @HystrixProperty(
                name="circuitBreaker.errorThresholdPercentage", value="60"
            )
        }
    )
    public String queryById(@PathVariable("id") Long id) {
        if(id % 2 == 0) {
            throw new RuntimeException();
        }
        String uri = "http://user-service/user/" + id;
        String user = restTemplate.getForObject(uri, String.class);
        return user;
    }

    public String fallbackMethod() {
        return "服务器hin忙~";
    }
}
