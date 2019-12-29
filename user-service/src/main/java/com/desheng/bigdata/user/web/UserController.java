package com.desheng.bigdata.user.web;

import com.desheng.bigdata.user.pojo.User;
import com.desheng.bigdata.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    Random random = new Random();
    @RequestMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = userService.queryById(id);
        return user;
    }
}
