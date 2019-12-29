package com.desheng.bigdata.consumer.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private String name;
    private Date birthday;
    private int gender;
    private Date created;
    private Date updated;
}
