package com.desheng.personas.mock.service;

import com.desheng.personas.mock.pojo.User;

import java.util.List;

public interface UserService {
    void insert(User t);
    void insertBatch(List<User> list);
}
