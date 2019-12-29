package com.desheng.personas.mock.pojo.log;

import lombok.Data;

/*
 购物车行为 Shopping Cart：
    商品id  商品类别id 、操作时间、操作类型（加入，删除）、用户id、终端类别、用户ip
 */
@Data
public class ShoppingCartLog {
    private long productId;//商品id
    private long categoryId;//商品类别id
    private long operationTime;//操作时间
    private long operationType;//操作类型（2加入，3 删除）
    private long userId;//用户id
    private int clientType;//终端类别
    private String userIp;//用户ip


    @Override
    public String toString() {
        return productId +
                "^" + categoryId +
                "^" + operationTime +
                "^" + operationType +
                "^" + userId +
                "^" + clientType +
                "^" + userIp;
    }
}
