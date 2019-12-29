package com.desheng.bigdata.personas.util

import com.desheng.bigdata.personas.common.db.JedisUtils
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies, OffsetRange}

import scala.collection.mutable

object KafkaManager {

    /**
      */
    def storeOffsets(offsetRanges: Array[OffsetRange], group:String) = {
        val jedis = JedisUtils.getJedis
        for (offsetRange <- offsetRanges) {
            val topic = offsetRange.topic
            val partition = offsetRange.partition
            val offset = offsetRange.untilOffset
            val field = s"${group}|${partition}"
            jedis.hset(topic, field, offset.toString)
        }
        JedisUtils.release(jedis)
    }

    def createMessage(ssc: StreamingContext, kafkaParams: Map[String, Object],
                      topics:Set[String]): InputDStream[ConsumerRecord[String, String]] = {
        //step 1 读取偏移量
        val fromOffsets:Map[TopicPartition, Long] = getFromOffsets(topics, kafkaParams("group.id").toString)
        var messages:InputDStream[ConsumerRecord[String, String]] = null
        if(!fromOffsets.isEmpty) {
            messages = KafkaUtils.
                createDirectStream[String, String](ssc,
                LocationStrategies.PreferConsistent,
                ConsumerStrategies.Subscribe[String, String](topics, kafkaParams, fromOffsets))

        } else {
            messages = KafkaUtils.
                createDirectStream[String, String](ssc,
                LocationStrategies.PreferConsistent,
                ConsumerStrategies.Subscribe[String, String](topics, kafkaParams))
        }
        messages
    }
    /*

     */
    def getFromOffsets(topics:Set[String], group:String):Map[TopicPartition, Long] = {
        val fromOffset = mutable.Map[TopicPartition, Long]()
        import scala.collection.JavaConversions._
        val jedis = JedisUtils.getJedis
        for(topic <- topics) {
            val map = jedis.hgetAll(topic)
            for((field, value) <- map) {//field=group|partition
                val partition = field.substring(field.indexOf("|") + 1).toInt
                val offset = value.toLong
                fromOffset.put(new TopicPartition(topic, partition), offset)
            }
        }
        JedisUtils.release(jedis)
        fromOffset.toMap
    }
}
