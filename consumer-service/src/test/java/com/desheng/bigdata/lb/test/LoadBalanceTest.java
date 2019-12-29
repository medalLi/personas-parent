package com.desheng.bigdata.lb.test;

import com.desheng.bigdata.consumer.ConsumerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)

public class LoadBalanceTest {


    @Autowired
    private LoadBalancerClient lbClient;

    @Test
    public void test1() {
        ServiceInstance instance = null;
        for (int i = 0; i < 20; i++) {
            instance = lbClient.choose("user-service");
            String host = instance.getHost();
            int port = instance.getPort();
            System.out.println(host + ":" + port);
        }
    }

}
