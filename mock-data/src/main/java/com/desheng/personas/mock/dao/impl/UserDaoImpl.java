package com.desheng.personas.mock.dao.impl;

import com.desheng.personas.mock.dao.UserDao;
import com.desheng.personas.mock.pojo.User;
import com.desheng.personas.mock.util.DruidDBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

/*
CREATE TABLE `pb_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `telephone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT 'email地址',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `idcard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `province` varchar(20) DEFAULT NULL COMMENT '收货地址之省份',
  `city` varchar(20) DEFAULT NULL COMMENT '说活地址之城市',
  `client_type` int(1) DEFAULT NULL COMMENT '终端类型：0，pc端；1，移动端；2，小程序；3，快应用',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
public class UserDaoImpl implements UserDao {

    private QueryRunner qr = new QueryRunner(DruidDBUtil.getDataSource());

    protected String insertSQL = "insert into pb_user (userid, username, password, gender, telephone, email, birthday, idcard, register_time, province, city, client_type) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    @Override
    public void insert(User user) {
        try {
            qr.update(insertSQL, user.getUserid(), user.getUsername(),
                    user.getPassword(), user.getGender(), user.getTelephone(),
                    user.getEmail(), user.getBirthday(), user.getIdcard(),
                    user.getRegisterTime(), user.getProvince(), user.getCity(), user.getClientType());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertBatch(List<User> list) {
        try {
            Object[][] params = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                Object[] obj = {
                        user.getUserid(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getGender(),
                        user.getTelephone(),
                        user.getEmail(),
                        user.getBirthday(),
                        user.getIdcard(),
                        user.getRegisterTime(),
                        user.getProvince(),
                        user.getCity(),
                        user.getClientType()
                };
                params[i] = obj;
            }
            qr.batch(insertSQL, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getRandomUser() {
        String sql = "SELECT * FROM pb_user ORDER BY RAND() LIMIT 1";
        try {
            return qr.query(sql, new BeanHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
