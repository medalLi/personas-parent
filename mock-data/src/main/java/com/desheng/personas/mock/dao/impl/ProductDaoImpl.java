package com.desheng.personas.mock.dao.impl;

import com.desheng.personas.mock.dao.ProductDao;
import com.desheng.personas.mock.pojo.Product;
import com.desheng.personas.mock.util.DruidDBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class ProductDaoImpl implements ProductDao {

    private QueryRunner qr = new QueryRunner(DruidDBUtil.getDataSource());

    @Override
    public Product getRandomProduct() {
        String sql = "SELECT * FROM pb_product ORDER BY RAND() LIMIT 1";
        try {
            return qr.query(sql, new BeanHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
