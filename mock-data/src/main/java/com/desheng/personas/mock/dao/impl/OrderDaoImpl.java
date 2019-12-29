package com.desheng.personas.mock.dao.impl;

import com.desheng.personas.mock.dao.OrderDao;
import com.desheng.personas.mock.pojo.Order;
import com.desheng.personas.mock.util.DruidDBUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

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
public class OrderDaoImpl implements OrderDao {

    private QueryRunner qr = new QueryRunner(DruidDBUtil.getDataSource());

    protected String insertSQL = "insert into pb_order (id, uid, pid, cid, create_time, pay_time, pay_type, pay_status, amount, coupon_amount, total_amount, refund_amount, num) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    @Override
    public void insert(Order order) {
        try {
            qr.update(insertSQL, order.getId(), order.getUser().getUserid(),
                    order.getProduct().getId(), order.getProduct().getCategory().getId(),
                    order.getCreateTime(), order.getPayTime(), order.getPayType(),
                    order.getPayStatus(), order.getAmount(), order.getCouponAmount(),
                    order.getTotalAmount(), order.getRefundAmount(), order.getNum());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertBatch(List<Order> list) {
        try {
            Object[][] params = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                Order order = list.get(i);
                Object[] obj = {
                        order.getId(),
                        order.getUser().getUserid(),
                        order.getProduct().getId(),
                        order.getProduct().getCategory().getId(),
                        order.getCreateTime(),
                        order.getPayTime(),
                        order.getPayType(),
                        order.getPayStatus(),
                        order.getAmount(),
                        order.getCouponAmount(),
                        order.getTotalAmount(),
                        order.getRefundAmount(),
                        order.getNum()
                };
                params[i] = obj;
            }
            qr.batch(insertSQL, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
