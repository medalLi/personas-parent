#!/bin/sh

###############################################################
##
## push order info 2 hdfs
## created by old li
## 2019/07/12 16:20:50
## 佛祖保佑，永无bug~
###############################################################

## hdfs://ns1/orginal/personas/user/yyyy/MM/dd/hhmm

SQOOP_BIN=/home/bigdata/app/sqoop/bin

ORDER_HOME_PREFIX=hdfs://ns1/orginal/personas/order/


## 半小时前的时间
START_TIME=`date -d"30 minute ago" +"%Y-%m-%d %H:%M:%S"`
## 当前时间
END_TIME=`date -d"0 day ago" +"%Y-%m-%d %H:%M:%S"`


## import 
QUERY_SQL=" 
     SELECT 
       o.* 
     FROM pb_order o 
     WHERE o.create_time >= '2019-05-29 11:38:32' 
     AND o.create_time < '${END_TIME}' 
     AND \$CONDITIONS
    "  

echo "QUERY_SQL":$QUERY_SQL

${SQOOP_BIN}/sqoop import \
--connect "jdbc:mysql://192.168.43.1:3306/personas?useUnicode=true&characterEncoding=UTF-8" \
--username bigdata \
--password sorry \
--delete-target-dir \
--fields-terminated-by '\001' \
--target-dir ${ORDER_HOME_PREFIX}/${INPUT_DATE_DIR} \
--query "${QUERY_SQL}" \
--split-by "id"
