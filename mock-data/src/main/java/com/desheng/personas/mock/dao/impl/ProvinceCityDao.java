package com.desheng.personas.mock.dao.impl;

import com.desheng.personas.mock.pojo.ProvinceCity;
import com.desheng.personas.mock.util.DruidDBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProvinceCityDao {
    private QueryRunner qr = new QueryRunner(DruidDBUtil.getDataSource());
    String sql = "SELECT " +
                 "    CONCAT(p.code, c.code) code, " +
                 "    p.province," +
                 "    c.city " +
                 "FROM pc_city c " +
                 "LEFT JOIN pc_province p ON c.pid = p.id " +
                 "WHERE p.cid = 1";

    public Map<String, ProvinceCity> queryProvinceAndCities() {
        Map<String, ProvinceCity> pcs = new HashMap<>();
        try {
            List<ProvinceCity> mapList = qr.query(sql, new BeanListHandler<ProvinceCity>(ProvinceCity.class));
            for (ProvinceCity pc : mapList) {
                pcs.put(pc.getCode(), pc);
            }
            return pcs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, ProvinceCity> provinces = new ProvinceCityDao().queryProvinceAndCities();

        for (Map.Entry<String, ProvinceCity> me : provinces.entrySet()) {
            System.out.println(me.getKey() + "-->" + me.getValue());
        }

    }
}
