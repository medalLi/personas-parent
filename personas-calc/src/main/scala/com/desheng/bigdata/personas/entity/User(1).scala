package com.desheng.bigdata.personas.entity

import java.util.Date

import com.desheng.bigdata.personas.common.Constants
import com.desheng.bigdata.personas.common.time.DateUtils

/*
CREATE TABLE `pb_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `telephone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT 'email地址',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `idcard` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `province` varchar(20) DEFAULT NULL COMMENT '收货地址之省份',
  `city` varchar(20) DEFAULT NULL COMMENT '说活地址之城市',
  `client_type` int(1) DEFAULT NULL COMMENT '终端类型：0，pc端；1，移动端；2，小程序；3，快应用',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=1010002 DEFAULT CHARSET=utf8

 */
case class User(
   userid:Long,
   username:String,
   password:String,
   gender:Short,
   telephone:String,
   email:String,
   birthday:String,
   idcard:String,
   registerTime: Date,
   province:String,
   city:String,
   clientType:Int) extends Serializable

object User {

    def line2User(line:String):User = {
        val fields = line.split(Constants.FILE_FIELD_SEPARATOR)
        if(fields == null || fields.length != 12) {
            User(-1, null, null, 0.toShort, null, null, null, null, null, null, null, 0)
        } else {
            val userid = fields(Constants.INDEX_USER_ID).toLong
            val username = fields(Constants.INDEX_USER_NAME)
            val password = fields(Constants.INDEX_USER_PASSWORD)
            val gender = if(fields(Constants.INDEX_USER_GENDER).toBoolean) 1.toShort else 0.toShort
            val telephone = fields(Constants.INDEX_USER_TELEPHONE)
            val email = fields(Constants.INDEX_USER_EMAIL)
            val birthday = fields(Constants.INDEX_USER_BIRTHDAY)
            val idcard = fields(Constants.INDEX_USER_ID_CARD)
            val registerTime = DateUtils.parseTime(fields(Constants.INDEX_USER_REGISTER_TIME))
            val province = fields(Constants.INDEX_USER_PROVINCE)
            val city = fields(Constants.INDEX_USER_CITY)
            val clientType = fields(Constants.INDEX_USER_CLIENT_TYPE).toInt
            User(userid, username, password, gender, telephone, email, birthday, idcard, registerTime, province, city, clientType)
        }
    }
}
