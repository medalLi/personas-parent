package com.desheng.personas.mock.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static DateFormat oDF = new SimpleDateFormat("yyyyMMdd");
    public static DateFormat nDF = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat sDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String dateFormat(String date) {
        try {
            return nDF.format(oDF.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
