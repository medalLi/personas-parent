package com.desheng.personas.mock.util;

import com.desheng.personas.mock.dao.impl.ProvinceCityDao;
import com.desheng.personas.mock.pojo.ProvinceCity;

import java.util.HashMap;
import java.util.Map;

public class ZoneUtil {
    private static Map<String, ProvinceCity> provinceAndCities = new HashMap<>();

    static {
        ProvinceCityDao pcDao = new ProvinceCityDao();
        provinceAndCities = pcDao.queryProvinceAndCities();
    }

    public static String getProvince(String idcard) {
        String cityCode = idcard.substring(0, 4);
        ProvinceCity provinceCity = provinceAndCities.get(cityCode);
        if(null == provinceCity) {
            return "北京";
        } else {
            return provinceCity.getProvince();
        }
    }

    public static String getCity(String idcard) {
        String cityCode = idcard.substring(0, 4);
        ProvinceCity provinceCity = provinceAndCities.get(cityCode);
        if(null == provinceCity) {
            return "北京";
        } else {
            return provinceCity.getCity();
        }
    }

    public static void main(String[] args) {
        System.out.println(getProvince("6211261988"));
        System.out.println(getCity("6211261988"));
    }
}
