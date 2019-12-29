package com.desheng.personas.mock.dao.impl;

import com.desheng.personas.mock.dao.BaseDao;
import com.desheng.personas.mock.util.DruidDBUtil;
import com.desheng.personas.mock.util.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {

    protected QueryRunner qr = new QueryRunner(DruidDBUtil.getDataSource());

    protected String insertSQL;
    //获取当前类上面的泛型，然后构建sql
    {
        //获得当前类带有泛型类型的父类
        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得运行期的泛型类型
        Class<T> clazz = (Class<T>) ptClass.getActualTypeArguments()[0];
        insertSQL = SqlUtil.buildSqlByClass(clazz);
    }

    @Override
    public void insert(T t) {

    }

    @Override
    public void insertBatch(List<T> list) {

    }
}
