package com.desheng.personas.mock;

import com.desheng.personas.mock.dao.UserDao;
import com.desheng.personas.mock.dao.impl.UserDaoImpl;
import com.desheng.personas.mock.pojo.User;
import com.desheng.personas.mock.pojo.UserDetail;
import com.desheng.personas.mock.service.UserService;
import com.desheng.personas.mock.service.impl.UserServiceImpl;
import com.desheng.personas.mock.util.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockUserData {
    public static void main(String[] args) {
        new Thread(new UserDataLogger()).start();
    }
    static class UserDataLogger implements Runnable{
        private UserService us = new UserServiceImpl();
        @Override
        public void run() {
            List<User> users = new ArrayList<>();
            //userid username password birthday gender  idcard phone email 住址
            long count = 0;
            for (int userid = 1000001; userid <= 1010001; userid++) {
                try {
                    //用户信息
                    User user = UserUtil.getUser(userid);
                    //用户详细信息
                    UserDetail userDetail = UserDetailUtil.getUserDetail(user);
                    user.setUserDetail(userDetail);
                    users.add(user);
                    if(users.size() % 1000 == 0) {
                        us.insertBatch(users);
                        System.out.println("batch size: " + users.size());
                        users = new ArrayList<>();
                    }
                    System.out.println("count=" + count++);
                } catch (StringIndexOutOfBoundsException e) {
                }
            }
            if(users.size() % 1000 != 0) {
                us.insertBatch(users);
            }
        }
    }
}
