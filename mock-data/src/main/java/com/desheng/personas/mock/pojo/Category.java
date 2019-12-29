package com.desheng.personas.mock.pojo;

import lombok.Data;

/*
CREATE TABLE `pb_category` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `level` int(11) DEFAULT NULL COMMENT '类别级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1183 DEFAULT CHARSET=utf8
 */
@Data
public class Category {
    private long id;
    private String name;
    private String description;
    private int level;
}
