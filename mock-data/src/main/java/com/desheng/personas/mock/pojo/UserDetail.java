package com.desheng.personas.mock.pojo;

import lombok.Data;

/*
CREATE TABLE `pb_user_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '详情补充表的id',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `education` int(1) DEFAULT NULL COMMENT '学历：0未知，1初中及其以下，2高中，3中专，4大专，5本科，6研究生',
  `profession` varchar(20) DEFAULT NULL COMMENT '职业',
  `marriage` int(1) DEFAULT NULL COMMENT '婚姻状况：0未婚，1已婚，2离异',
  `has_children` int(11) DEFAULT NULL COMMENT '是否有小孩，0无小孩，1有小孩',
  `has_car` int(11) DEFAULT NULL COMMENT '是否有车：0无车，1有车',
  `has_house` int(11) DEFAULT NULL COMMENT '是否有房：0无房，1有房',
  `phone_brand` varchar(20) NOT NULL COMMENT '手机品牌',
  PRIMARY KEY (`detail_id`),
  KEY `user_id_fk` (`userid`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`userid`) REFERENCES `pb_user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Data
public class UserDetail {
    private long detailId;
    private long userid;
    private int education;
    private String profession;
    private int marriage;
    private int hasChildren;
    private int hasCar;
    private int hasHouse;
    private String phoneBrand;
}
