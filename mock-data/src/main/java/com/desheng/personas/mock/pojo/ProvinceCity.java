package com.desheng.personas.mock.pojo;

import lombok.Data;

@Data
public class ProvinceCity {
    private String code;
    private String province;
    private String city;

    public ProvinceCity() {
    }

    public ProvinceCity(String province, String city) {
        this.province = province;
        this.city = city;
    }
}
