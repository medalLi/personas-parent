package com.desheng.bigdata.personas.common.db;

import com.desheng.bigdata.personas.common.time.DateUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.util.LinkedList;

public class HBaseUtils {


    private static LinkedList<Connection> pool = new LinkedList<>();


    static {
        try {
            Configuration conf = HBaseConfiguration.create();
            for(int i = 0; i < 10; i++) {
                pool.push(ConnectionFactory.createConnection(conf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getHBaseConnection() {
        while (pool.isEmpty()) {
            DateUtils.sleep(1000);
        }
        return pool.poll();
    }


    public static void release(Connection connection) {
        pool.push(connection);
    }
}
