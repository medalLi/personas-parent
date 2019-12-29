package com.desheng.personas.mock.dao.impl;

import com.desheng.personas.mock.dao.CategoryDao;
import com.desheng.personas.mock.pojo.Category;
import com.desheng.personas.mock.util.DruidDBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class CategoryDaoImpl implements CategoryDao {
    private QueryRunner qr = new QueryRunner(DruidDBUtil.getDataSource());

    @Override
    public Category getCategoryById(long cid) {
        String sql = "select * from pb_category where id=?";
        try {
            return qr.query(sql, new BeanHandler<Category>(Category.class), cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
