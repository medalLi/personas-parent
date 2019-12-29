package com.desheng.personas.mock.pojo.log;

import lombok.Data;

/*
 浏览商品行为 scan：商品id 商品类别id 浏览时间、停留时间、用户id 终端类别,用户ip
 */
@Data
public class ScanLog {
    private long productId;//商品id
    private long categoryId;//商品类别id
    private long scanTime;//浏览时间
    private long stayLen;//停留时间
    private long userId;//用户id
    private int clientType;//终端类别
    private String userIp;//用户ip


    @Override
    public String toString() {
        return productId +
                "^" + categoryId +
                "^" + scanTime +
                "^" + stayLen +
                "^" + userId +
                "^" + clientType +
                "^" + userIp;
    }
}
