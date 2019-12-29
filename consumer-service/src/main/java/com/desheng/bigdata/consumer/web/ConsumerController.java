package com.desheng.bigdata.consumer.web;

import com.desheng.bigdata.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        String uri = "http://localhost/user/" + id;
        User user = restTemplate.getForObject(uri, User.class);
        return user;
    }
}
