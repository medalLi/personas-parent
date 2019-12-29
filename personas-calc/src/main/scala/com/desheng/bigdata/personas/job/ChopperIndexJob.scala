package com.desheng.bigdata.personas.job

import com.desheng.bigdata.personas.common.Constants
import com.desheng.bigdata.personas.common.db.HBaseUtils
import com.desheng.bigdata.personas.common.number.NumberUtils
import com.desheng.bigdata.personas.entity.Order
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.Put
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
/**
  * 剁手指数统计
  * 需要加载的pb_order表中的数据
  * 将结果录入到hbase中的user表
  */
object ChopperIndexJob {
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
            .setAppName("ChopperIndexJob")
        val spark = SparkSession.builder()
                    .config(conf)
                    .getOrCreate()

        //加载外部数据
        import spark.implicits._
        val lines = spark.sparkContext.textFile(inputpath)
        val orderRDD:RDD[Order] = lines.map(line => Order.makeLine2Order(line))

        val orderDS:Dataset[Order] = orderRDD.toDS()

        orderDS.createOrReplaceTempView("pb_order")

        val sql =
            """
              |SELECT
              |   uid,
              |   ROUND(SUM(amount) / COUNT(1) / 10000, 2) kdj,
              |   ROUND(MAX(amount) / 10000, 2) zdje,
              |   ROUND(COUNT(1) / 52, 2) frequency
              |FROM pb_order
              |GROUP BY uid
            """.stripMargin
        val uid2Info:DataFrame = spark.sql(sql)
        //加权求取的剁手指数 剁手指数计算公式=支付金额平均值 * 0.3 + 支付金额最大值 * 0.3 + 下单频率 * 0.4

        //将结果录入到hbase表中
        uid2Info.foreachPartition(rows => {
            val connection = HBaseUtils.getHBaseConnection
            val table = connection.getTable(TableName.valueOf(Constants.TABLE_USER_BASE_TAG))
            for(row <- rows) {
                val rowkey = row.getAs[Long]("uid").toString.reverse.getBytes()

                val kdj = row.getAs[Double]("kdj")//客单价
                val zdje = row.getAs[Double]("zdje")//最大金额
                val frequency = row.getAs[Double]("frequency") // 剁手频率

                var chopperIndex = (0.3 * kdj + 0.3 * zdje + 0.4 * frequency) % 100
                chopperIndex = NumberUtils.formatDouble(chopperIndex, 2)
                val put = new Put(rowkey)

                put.addColumn(Constants.CF_BASE_USER, Constants.COL_CHOPPER_INDEX, chopperIndex.toString.getBytes())
                table.put(put)
            }
            table.close()
            HBaseUtils.release(connection)
        })
        spark.stop()
    }
}
