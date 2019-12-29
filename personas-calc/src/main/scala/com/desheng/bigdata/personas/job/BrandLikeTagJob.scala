package com.desheng.bigdata.personas.job

import com.desheng.bigdata.personas.util.KafkaManager
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{HasOffsetRanges, KafkaUtils}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 用户品牌偏好业务统计
  * 从用户的业务行为数据中判断用户的品牌偏好，所以这里我们就通过
  * SparkStreaming实时整合kafka，读取用户的浏览、收藏、关注等等数据进行判断
  */
object BrandLikeTagJob {
    def main(args: Array[String]): Unit = {

        if(args == null || args.length < 2) {
            System.err.println(
                """
                  |Parameter Errors ! Usage: <batchInterval> <topics>
                """.stripMargin)
            System.exit(-1)
        }

        val Array(batchInterval, topicStr) = args

        val conf = new SparkConf()
                .setAppName("BrandLikeTagJob")
                .setMaster("local[*]")

        val ssc = new StreamingContext(conf, Seconds(batchInterval.toLong))
        val kafkaParams = Map[String, Object](
            "bootstrap.servers" -> "bigdata01:9092,bigdata02:9092,bigdata03:9092",
            "key.deserializer" -> classOf[StringDeserializer],
            "value.deserializer" -> classOf[StringDeserializer],
            "group.id" -> "personas_1901_tags",
            "auto.offset.reset" -> "earliest"
        )
        val topics = topicStr.split(",").toSet
        //加载kafka中的数据
        val messages:InputDStream[ConsumerRecord[String, String]] = KafkaManager.createMessage(ssc, kafkaParams, topics)



        messages.foreachRDD((rdd, bTime) => {
            if(!rdd.isEmpty()) {
                println("-------------------------------------------")
                println(s"Time: $bTime")
                println(s"################rdd's count: " + rdd.count())
                println("-------------------------------------------")
                KafkaManager.storeOffsets(rdd.asInstanceOf[HasOffsetRanges].offsetRanges, kafkaParams("group.id").toString)
            }
        })

        ssc.start()
        ssc.awaitTermination()
    }
}
