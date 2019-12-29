package com.desheng.bigdata.user.service.impl;

import com.desheng.bigdata.user.mapper.UserMapper;
import com.desheng.bigdata.user.pojo.User;
import com.desheng.bigdata.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
