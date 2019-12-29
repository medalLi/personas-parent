package com.desheng.personas.mock.service.impl;

import com.desheng.personas.mock.dao.UserDao;
import com.desheng.personas.mock.dao.UserDetailDao;
import com.desheng.personas.mock.dao.impl.UserDaoImpl;
import com.desheng.personas.mock.dao.impl.UserDetailDaoImpl;
import com.desheng.personas.mock.pojo.User;
import com.desheng.personas.mock.pojo.UserDetail;
import com.desheng.personas.mock.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private UserDetailDao udDao = new UserDetailDaoImpl();

    @Override
    public void insert(User user) {
        userDao.insert(user);
        udDao.insert(user.getUserDetail());
    }

    @Override
    public void insertBatch(List<User> list) {
        userDao.insertBatch(list);

        List<UserDetail> uds = new ArrayList<>();
        for (User user : list) {
            uds.add(user.getUserDetail());
        }
        udDao.insertBatch(uds);
    }
}
