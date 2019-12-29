package com.desheng.personas.mock.pojo.log;

import lombok.Data;

/*
收藏商品行为 Collection：
    商品id 商品类别id 操作时间、操作类型（收藏，取消）、用户id、终端类别、用户ip
 */
@Data
public class CollectionLog {
    private long productId;//商品id
    private long categoryId;//商品类别id
    private long operationTime;//操作时间
    private int operationType;//操作类型（1 收藏，0取消）
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
