package com.desheng.personas.mock.pojo;

import lombok.Data;

import java.util.Date;

/*
CREATE TABLE `pb_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `cid` int(20) DEFAULT NULL COMMENT '商品类别id',
  `mechart_id` int(20) DEFAULT NULL COMMENT '商家id',
  `name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '商品描述',
  `price` float DEFAULT NULL COMMENT '商品价格',
  `num` int(20) DEFAULT NULL COMMENT '库存',
  `pic_url` varchar(500) DEFAULT NULL COMMENT '图片地址',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `category_id_fk` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=143771131488370 DEFAULT CHARSET=utf8
 */
@Data
public class Product {
    private long id;
    private long cid;
    private long mechart_id;
    private String name;
    private String description;
    private float price;
    private long num;
    private String pic_url;
    private String brand;
    private Date create_time;
    private Date update_time;

    private Category category;
}
