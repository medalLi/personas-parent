package com.desheng.personas.mock;

import com.desheng.personas.mock.constants.OperationType;
import com.desheng.personas.mock.dao.UserDao;
import com.desheng.personas.mock.dao.impl.UserDaoImpl;
import com.desheng.personas.mock.pojo.Product;
import com.desheng.personas.mock.pojo.User;
import com.desheng.personas.mock.pojo.log.CollectionLog;
import com.desheng.personas.mock.pojo.log.FollowLog;
import com.desheng.personas.mock.pojo.log.ScanLog;
import com.desheng.personas.mock.pojo.log.ShoppingCartLog;
import com.desheng.personas.mock.service.ProductService;
import com.desheng.personas.mock.service.impl.ProductServiceImpl;
import com.desheng.personas.mock.util.IpUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 浏览商品行为 scan：商品id 商品类别id 浏览时间、停留时间、用户id 终端类别,用户ip
 收藏商品行为 Collection：商品id 商品类别id 操作时间、操作类型（收藏，取消）、用户id、终端类别、用户ip
 购物车行为 Shopping Cart：商品id 商品类别id 、操作时间、操作类型（加入，删除）、用户id、终端类别、用户ip
 关注商品Follow:商品id 商品类别id 操作时间、操作类型（关注，取消）、用户id、终端类别、用户ip
 */
public class MockLogData {

    public static void main(String[] args) {
        MockLogData mld = new MockLogData();
        mld.produceLog();
    }

    public void produceLog() {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        threadPool.execute(new ScanThread());
        threadPool.execute(new CollectionThread());
        threadPool.execute(new FollowThread());
        threadPool.execute(new CartThread());
    }

    private UserDao userDao = new UserDaoImpl();
    private ProductService ps = new ProductServiceImpl();
    private Random random = new Random();
    class ScanThread implements Runnable {
        private Logger scanLogger = Logger.getLogger("scan");
        @Override
        public void run() {
            while (true) {
                //商品id 商品类别id 浏览时间、停留时间、用户id 终端类别,用户ip
                User user = userDao.getRandomUser();
                Product product = ps.getRandomProduct();
                ScanLog scanLog = new ScanLog();
                //商品id
                scanLog.setProductId(product.getId());
                //商品类别id
                scanLog.setCategoryId(product.getCategory().getId());
                //浏览时间
                scanLog.setScanTime(System.currentTimeMillis());
                //停留时长 单位毫秒
                scanLog.setStayLen(random.nextInt(301) * 1000);//5分钟以内
                //用户id
                scanLog.setUserId(user.getUserid());
                //终端类别
                scanLog.setClientType(user.getClientType());
                //用户ip
                scanLog.setUserIp(IpUtil.randomIp());
                scanLogger.info(scanLog.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class CollectionThread implements Runnable {
        private Logger collectLogger = Logger.getLogger("collect");
        @Override
        public void run() {
            while (true) {
                //商品id 商品类别id 操作时间、操作类型（收藏，取消）、用户id、终端类别、用户ip
                User user = userDao.getRandomUser();
                Product product = ps.getRandomProduct();
                CollectionLog collectLog = new CollectionLog();
                //商品id
                collectLog.setProductId(product.getId());
                //商品类别id
                collectLog.setCategoryId(product.getCategory().getId());
                //浏览时间
                collectLog.setOperationTime(System.currentTimeMillis());
                //操作类型（收藏，取消）
                collectLog.setOperationType(OperationType.randomCollectType().getId());
                //用户id
                collectLog.setUserId(user.getUserid());
                //终端类别
                collectLog.setClientType(user.getClientType());
                //用户ip
                collectLog.setUserIp(IpUtil.randomIp());
                collectLogger.info(collectLog.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class CartThread implements Runnable {
        private Logger cartLogger = Logger.getLogger("cart");
        @Override
        public void run() {
            while (true) {
                //商品id 商品类别id 、操作时间、操作类型（加入，删除）、用户id、终端类别、用户ip
                User user = userDao.getRandomUser();
                Product product = ps.getRandomProduct();
                ShoppingCartLog cartLog = new ShoppingCartLog();
                //商品id
                cartLog.setProductId(product.getId());
                //商品类别id
                cartLog.setCategoryId(product.getCategory().getId());
                //浏览时间
                cartLog.setOperationTime(System.currentTimeMillis());
                //操作类型（加入，删除）
                cartLog.setOperationType(OperationType.randomCartType().getId());
                //用户id
                cartLog.setUserId(user.getUserid());
                //终端类别
                cartLog.setClientType(user.getClientType());
                //用户ip
                cartLog.setUserIp(IpUtil.randomIp());
                cartLogger.info(cartLog.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class FollowThread implements Runnable {
        private Logger followLogger = Logger.getLogger("follow");
        @Override
        public void run() {
            while (true) {
                //商品id 商品类别id 操作时间、操作类型（关注，取消）、用户id、终端类别、用户ip
                User user = userDao.getRandomUser();
                Product product = ps.getRandomProduct();
                FollowLog followLog = new FollowLog();
                //商品id
                followLog.setProductId(product.getId());
                //商品类别id
                followLog.setCategoryId(product.getCategory().getId());
                //浏览时间
                followLog.setOperationTime(System.currentTimeMillis());
                //操作类型（收藏，取消）
                followLog.setOperationType(OperationType.randomFollowType().getId());
                //用户id
                followLog.setUserId(user.getUserid());
                //终端类别
                followLog.setClientType(user.getClientType());
                //用户ip
                followLog.setUserIp(IpUtil.randomIp());
                followLogger.info(followLog.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
