package com.desheng.personas.mock.pojo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
CREATE TABLE `pb_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `telephone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT 'email地址',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `idcard` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `province` varchar(20) DEFAULT NULL COMMENT '收货地址之省份',
  `city` varchar(20) DEFAULT NULL COMMENT '说活地址之城市',
  `client_type` int(1) DEFAULT NULL COMMENT '终端类型：0，pc端；1，移动端；2，小程序；3，快应用',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000101 DEFAULT CHARSET=utf8
 */
@Data
public class User {
    private long userid;
    private String username;
    private String password;
    private short gender;
    private String telephone;
    private String email;
    private String birthday;
    private String idcard;
    private Date registerTime;
    private String province;
    private String city;
    private int clientType;
    //用户详细信息
    private UserDetail userDetail;



    public int getAge() {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        int age = Integer.valueOf(yearFormat.format(new Date())) -  Integer.valueOf(birthday.substring(0, 4));
        return age;
    }
}
