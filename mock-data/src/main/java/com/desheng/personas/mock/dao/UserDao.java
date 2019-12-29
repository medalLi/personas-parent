package com.desheng.personas.mock.dao;

import com.desheng.personas.mock.pojo.User;

public interface UserDao extends BaseDao<User> {
    public User getRandomUser();
}
