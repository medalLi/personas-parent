package com.desheng.personas.mock.util;

import java.lang.reflect.Field;

public class SqlUtil {

    public static <T> String buildSqlByClass(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return null;
    }
}
