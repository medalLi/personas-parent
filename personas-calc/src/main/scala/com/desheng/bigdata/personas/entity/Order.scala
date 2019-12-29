package com.desheng.bigdata.personas.entity

import java.util.Date

import com.desheng.bigdata.personas.common.Constants
import com.desheng.bigdata.personas.common.time.DateUtils
import org.apache.spark.sql.Row

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
) ENGINE=InnoDB AUTO_INCREMENT=300001 DEFAULT CHARSET=utf8

 */
case class Order(
        id: Long,
        uid: Long,
        pid: Long,
        cid: Long,
        createTime: String,
        payTime: String,
        payType: Int,
        payStatus: Int,
        amount: Double,
        couponAmount: Double,
        totalAmount: Double,
        refundAmount: Double,
        num: Int)

object Order {

    def makeLine2Order(str:String):Order = {
        val fields = str.split(Constants.FILE_FIELD_SEPARATOR)
        if(fields == null || fields.length != 13) {
            Order(-1, -1, -1, -1, null, null, -1, -1, -1, -1, -1, -1, -1)
        } else {
            val oid = fields(Constants.INDEX_ORDER_ID).toLong
            val uid = fields(Constants.INDEX_ORDER_UID).toLong
            val pid = fields(Constants.INDEX_ORDER_PID).toLong
            val cid = fields(Constants.INDEX_ORDER_CID).toLong
            val createTime = fields(Constants.INDEX_ORDER_CREATE_TIME)
            val payTime = fields(Constants.INDEX_ORDER_PAY_TIME)
            val payType = fields(Constants.INDEX_ORDER_PAY_TYPE).toInt
            val payStatus = fields(Constants.INDEX_ORDER_PAY_STATUS).toInt
            val amount = fields(Constants.INDEX_ORDER_AMOUNT).toDouble
            val couponAmount = fields(Constants.INDEX_ORDER_COUPON_AMOUNT).toDouble
            val totalAmount = fields(Constants.INDEX_ORDER_TOTAL_AMOUNT).toDouble
            val refundAmount = fields(Constants.INDEX_ORDER_REFUND_AMOUNT).toDouble
            val num = fields(Constants.INDEX_ORDER_NUM).toInt

            Order(oid, uid, pid, cid, createTime, payTime, payType, payStatus, amount, couponAmount, totalAmount, refundAmount, num)
        }
    }

}
