package com.desheng.bigdata.personas.job

import com.desheng.bigdata.personas.common.Constants
import com.desheng.bigdata.personas.common.db.{HBaseUtils, JedisUtils}
import com.desheng.bigdata.personas.common.device.{DeviceUtils, EmailUtils}
import com.desheng.bigdata.personas.entity.User
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.Put
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
  * 用户的基础标签统计作业
  *     用户年代
  *     手机运营商
  *     邮箱运营商
  *
  *  spark-core的操作
  */
object UserBaseTagJob {
    def main(args: Array[String]): Unit = {
        if(args == null || args.length < 1) {
            System.err.println(
                """
                  |Parameter Errors ! Usage: <inputpath>
                """.stripMargin)
            System.exit(-1)
        }

        val Array(inputpath) = args

        val conf = new SparkConf()
                .setMaster("local[*]")
                .setAppName("UserBaseTagJob")
        val sc = new SparkContext(conf)

        //加载外部数据
        val lines = sc.textFile(inputpath)

        val users:RDD[User] = lines.map(line => User.line2User(line)).filter(user => user.userid != -1L)

        //基于转换之后的user数据进行统计
        /*val userTagPairs = users.flatMap(user => {

            val ab = ArrayBuffer[(String, Int)]()
            //处理用户年代
            val yearAge = calcYearAge(user.birthday)
            //处理手机号 -->运营商
            val telOperator = getTelOperator(user.telephone)
            //处理邮箱
            val emailOperator = getEmailOperator(user.email)


            ab.append((yearAge, 1))
            ab.append((telOperator, 1))
            ab.append((emailOperator, 1))

            ab
        }).reduceByKey(_+_)*/

        val userTagPairs = users.mapPartitions(partition => {
            val ab = ArrayBuffer[(String, Int)]()
            val connection = HBaseUtils.getHBaseConnection
            val table = connection.getTable(TableName.valueOf(Constants.TABLE_USER_BASE_TAG))

            partition.foreach(user => {
                //处理用户年代
                val yearAge = calcYearAge(user.birthday)
                //处理手机号 -->运营商
                val telOperator = getTelOperator(user.telephone)
                //处理邮箱
                val emailOperator = getEmailOperator(user.email)
                val rowkey = user.userid.toString.reverse.getBytes()
                val put = new Put(rowkey)//使用useride作为行键

                put.addColumn(Constants.CF_BASE_USER, Constants.COL_YEAR_AGE, yearAge.getBytes())
                put.addColumn(Constants.CF_BASE_USER, Constants.COL_TEL_OP, telOperator.getBytes())
                put.addColumn(Constants.CF_BASE_USER, Constants.COL_EMAIL_OP, emailOperator.getBytes())

                table.put(put)
                ab.append((yearAge, 1))
                ab.append((telOperator, 1))
                ab.append((emailOperator, 1))
            })
            table.close()
            HBaseUtils.release(connection)

            ab.iterator
        }).reduceByKey(_+_)

        /**
          *  用户个体的标签数据
          *     写入hbase
          *  汇总的数据
          *     增量的更新---使用redis/mongodb一次搞定 incrBy
          */
        userTagPairs.foreachPartition(partition => {
            val jedis = JedisUtils.getJedis
            partition.foreach{case (key, count) => {
                val prefix = key.substring(0, key.indexOf("_"))
                val tag = key.substring(key.indexOf("_") + 1)
                prefix match {
                    case Constants.PREFIX_YEAR_AGE => {
                        jedis.hincrBy(Constants.TABLE_USER_SUMMARY_YEAR, tag, count)
                    }
                    case Constants.PREFIX_TEL => {
                        jedis.hincrBy(Constants.TABLE_USER_SUMMARY_TEL, tag, count)
                    }
                    case Constants.PREFIX_EMAIL => {
                        jedis.hincrBy(Constants.TABLE_USER_SUMMARY_EMAIL, tag, count)
                    }
                }
            }}
            JedisUtils.release(jedis)
        })
        sc.stop()
    }

    //yyyy-MM-dd
    def calcYearAge(birthday: String): String = {
        Constants.PREFIX_YEAR_AGE + "_" + birthday.charAt(2) + "0s"
    }

    def getTelOperator(tel: String): String = {
        s"${Constants.PREFIX_TEL}_${DeviceUtils.getOperatorByTel(tel)}"
    }
    def getEmailOperator(email: String): String = {
        s"${Constants.PREFIX_EMAIL}_${EmailUtils.getOperatorByEmail(email)}"
    }
}
