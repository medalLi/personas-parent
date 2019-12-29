package com.desheng.personas.mock.dao;

import java.util.List;

public interface BaseDao<T> {
    void insert(T t);
    void insertBatch(List<T> list);
}
