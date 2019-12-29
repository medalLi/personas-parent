package com.desheng.personas.mock.util;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DruidDBUtil {

    private static DruidDataSource ds;
    static {
        Properties properties = new Properties();
        try {
            properties.load(DruidDBUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties"));
            ds = new DruidDataSource();
            ds.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
            ds.setUrl(properties.getProperty("jdbc.url"));
            ds.setUsername(properties.getProperty("jdbc.username"));
            ds.setPassword(properties.getProperty("jdbc.password"));
            ds.setDefaultAutoCommit(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
