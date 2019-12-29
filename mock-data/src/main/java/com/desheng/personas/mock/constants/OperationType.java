package com.desheng.personas.mock.constants;

import java.util.Random;

/*
 收藏商品行为 Collection：操作类型（收藏，取消）
 购物车行为 Shopping Cart：操作类型（加入，删除）
 关注商品Follow:操作类型（关注，取消）
 */
public enum OperationType {
    COLLECTION("收藏", 0),
    COLLECTION_CANCEL("取消收藏", 1),
    CART_JOIN("加入购物车", 2),
    CART_CANCEL("移除购物车", 3),
    FOLLOW("关注", 4),
    FOLLOW_CANCEL("取消关注", 5);

    private int id;
    private String type;
    private OperationType(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
    private static Random random = new Random();

    public static OperationType randomCollectType() {
        int type = random.nextInt(2);
        if(type == 0) {
            return OperationType.COLLECTION;
        } else {
            return OperationType.COLLECTION_CANCEL;
        }
    }

    public static OperationType randomCartType() {
        int type = random.nextInt(2) + 2;
        if(type == 2) {
            return OperationType.CART_JOIN;
        } else {
            return OperationType.CART_CANCEL;
        }
    }

    public static OperationType randomFollowType() {
        int type = random.nextInt(2) + 4;
        if(type == 4) {
            return OperationType.FOLLOW;
        } else {
            return OperationType.FOLLOW_CANCEL;
        }
    }
}
