package com.desheng.personas.mock;

import com.desheng.personas.mock.dao.OrderDao;
import com.desheng.personas.mock.dao.UserDao;
import com.desheng.personas.mock.dao.impl.OrderDaoImpl;
import com.desheng.personas.mock.dao.impl.UserDaoImpl;
import com.desheng.personas.mock.pojo.Order;
import com.desheng.personas.mock.pojo.Product;
import com.desheng.personas.mock.pojo.User;
import com.desheng.personas.mock.service.ProductService;
import com.desheng.personas.mock.service.impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MockOrderData {
    public static void main(String[] args) {
        MockOrderData md = new MockOrderData();
        md.start();
    }

    private void start() {
        OrderDao orderDao = new OrderDaoImpl();
        UserDao userDao = new UserDaoImpl();
        ProductService ps = new ProductServiceImpl();
        List<Order> list = new ArrayList<>();
         int count = 1;
        for (int oid = 100001; oid < 1100001; oid++) {
            Order order = new Order();
            //oid
            order.setId(oid);
            //uid
            User user = userDao.getRandomUser();
            order.setUser(user);
            //pid
            //cid
            Product product = ps.getRandomProduct();
            order.setProduct(product);
            //create_time
            order.setCreateTime(new Date());
            //pay_time
            order.setPayTime(new Date());
            //payType
            order.setPayType(getPayType());
            //payStatus
            order.setPayStatus(getPayStatus());
            //num
            order.setNum(random.nextInt(10));
            //total_amount
            order.setTotalAmount(product.getPrice() * order.getNum());
            //coupon_amount
            order.setCouponAmount(getCouponAmount(order));
            //amount
            order.setAmount(order.getTotalAmount() - order.getCouponAmount());
            //refund_amount
            order.setRefundAmount(getRefundAmount(order));
            list.add(order);
            System.out.println("count: " + count++);
            if(list.size() % 5000 == 0) {
                orderDao.insertBatch(list);
                list = new ArrayList<>();
                System.out.println("batch: " + count);
            }
        }

        if(list.size() % 5000 != 0) {
            orderDao.insertBatch(list);
        }
        System.out.println("final count: " + count);
    }

    Random random = new Random();

    /*
        0   网银
        1   支付宝
        2   微信
        3   信用卡
      */
    public int getPayType() {
        return random.nextInt(4);
    }
    /*
        支付状态：
        0 未支付
        1 已支付
        2 支付成功
        3 支付失败
        4 退款中
        5 退款成功
     */
    public int getPayStatus() {
        return random.nextInt(6);
    }
    private double getRefundAmount(Order order) {
        if(order.getPayStatus() < 4) {
            return 0;
        } else {
            return order.getAmount();
        }
    }

    //优惠券
    private double getCouponAmount(Order order) {
        Double amount = order.getTotalAmount();
        if(amount > 10000) {
            return 2000;
        } else if(amount > 5000) {
            return 999;
        } else if(amount > 1000) {
            return 500;
        } else if(amount > 500) {
            return 99;
        } else if(amount > 200) {
            return 50;
        } else if (amount > 100) {
            return 20;
        } else {
            return random.nextInt(20);
        }
    }
}
