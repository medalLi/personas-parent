package com.desheng.personas.mock.pojo;

import lombok.Data;

import java.util.Date;

/*
CREATE TABLE `pb_order` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` int(20) DEFAULT NULL COMMENT '用户id',
  `pid` int(20) DEFAULT NULL COMMENT '商品id',
  `cid` int(20) DEFAULT NULL COMMENT '类别id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `pay_type` int(2) DEFAULT NULL COMMENT '支付类型',
  `pay_status` int(2) DEFAULT NULL COMMENT '支付状态',
  `amount` double DEFAULT NULL COMMENT '支付金额',
  `coupon_amount` double DEFAULT NULL COMMENT '优惠券金额',
  `total_amount` double DEFAULT NULL COMMENT '总金额',
  `refund_amount` double DEFAULT NULL COMMENT '退款金额',
  `num` int(11) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Data
public class Order {
    private long id;
    private User user;
    private Product product;
    private Category category;
    private Date createTime;
    private Date payTime;
    private int payType;
    private int payStatus;
    private double amount;
    private double couponAmount;
    private double totalAmount;
    private double refundAmount;
    private int num;
}
