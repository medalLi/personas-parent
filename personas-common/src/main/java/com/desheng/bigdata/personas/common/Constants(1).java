package com.desheng.bigdata.personas.common;

public interface Constants {

    String FILE_FIELD_SEPARATOR = "\001"; //加载hdfs文件的分隔符

    String PREFIX_YEAR_AGE = "year";
    String PREFIX_EMAIL = "email";
    String PREFIX_TEL = "tel";

    ////////////////user-base-tag-table-info///////////
    String TABLE_USER_BASE_TAG = "personas_user_base_tag";
    byte[] CF_BASE_USER = "base_tag".getBytes();
    byte[] COL_YEAR_AGE = "year_age".getBytes();
    byte[] COL_TEL_OP = "tel_op".getBytes();
    byte[] COL_EMAIL_OP = "email_op".getBytes();
    byte[] COL_CHOPPER_INDEX = "chopper_index".getBytes();//剁手指数
    ///////////////user-tag-summary////////////////////
    String TABLE_USER_SUMMARY_YEAR = "personas_summary_year";
    String TABLE_USER_SUMMARY_TEL = "personas_summary_tel";
    String TABLE_USER_SUMMARY_EMAIL = "personas_summary_email";
    /////////////user表中字段的索引/////////////////
    int INDEX_USER_ID = 0; //userid
    int INDEX_USER_NAME = 1; //username
    int INDEX_USER_PASSWORD = 2;//password
    int INDEX_USER_GENDER = 3;//gender
    int INDEX_USER_TELEPHONE = 4;//telephone
    int INDEX_USER_EMAIL = 5;//email
    int INDEX_USER_BIRTHDAY = 6;//birthday
    int INDEX_USER_ID_CARD = 7;//idcard
    int INDEX_USER_REGISTER_TIME = 8;//registertime
    int INDEX_USER_PROVINCE = 9;//province
    int INDEX_USER_CITY = 10;//city
    int INDEX_USER_CLIENT_TYPE = 11;//clienttype
    /////////////user-detail表中字段的索引/////////////////

    /////////////order表中字段的索引/////////////////

    int INDEX_ORDER_ID = 0;
    int INDEX_ORDER_UID = 1;
    int INDEX_ORDER_PID = 2;
    int INDEX_ORDER_CID = 3;
    int INDEX_ORDER_CREATE_TIME = 4;
    int INDEX_ORDER_PAY_TIME = 5;
    int INDEX_ORDER_PAY_TYPE = 6;
    int INDEX_ORDER_PAY_STATUS = 7;
    int INDEX_ORDER_AMOUNT = 8;
    int INDEX_ORDER_COUPON_AMOUNT = 9;
    int INDEX_ORDER_TOTAL_AMOUNT = 10;
    int INDEX_ORDER_REFUND_AMOUNT = 11;
    int INDEX_ORDER_NUM = 12;


}
