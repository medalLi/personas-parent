package com.desheng.personas.mock.util;

import com.desheng.personas.mock.pojo.User;
import com.desheng.personas.mock.pojo.UserDetail;

import java.util.Random;

public class UserDetailUtil {
    public static UserDetail getUserDetail(User user) {
        UserDetail userDetail = new UserDetail();
        //userid
        userDetail.setUserid(user.getUserid());
        //detailid
        userDetail.setDetailId(getDetailId(user.getUserid()));
        //education
        userDetail.setEducation(getEducation());
        //profession
        userDetail.setProfession(getProfession());
        //marriage
        userDetail.setMarriage(marriageOrNot(user));
        //has_children
        userDetail.setHasChildren(getChildren(userDetail.getMarriage()));
        //has_car
        userDetail.setHasCar(hasCar(user));
        //has_house
        userDetail.setHasHouse(hasHouse(user));
        //phone_brand
        userDetail.setPhoneBrand(phoneBrand());
        return userDetail;
    }

    static Random random = new Random();


    //1000010 --->11000010
    public static long getDetailId(long userid) {
        return Long.valueOf("1" + userid);
    }

    //学历：0未知，1初中及其以下，2高中，3中专，4大专，5本科，6研究生
    public static int getEducation() {
        return random.nextInt(7);
    }
    public static String professions [] = {
            "软件工程师", "软件测试工程师", "数据库管理员", "系统安全工程师",
            "系统集成工程师", "美工", "大数据开发工程师", "网络工程师",
            "产品经理", "网站运营专员", "系统架构师", "电子商务总监"
    };
    public static String getProfession() {
        return professions[random.nextInt(professions.length)];
    }

    //婚姻状况：0未婚，1已婚，2离异

    public static int marriageOrNot(User user) {
        if(user.getGender() == 0 && user.getAge() < 20) {
            return 0;
        } else if(user.getGender() == 1 && user.getAge() < 22) {
            return 0;
        } else {
            return random.nextInt(3);
        }
    }

    //是否有小孩，0无小孩，1有小孩
    public static int getChildren(int marriage) {
        if(marriage == 0) {
            return 0;
        } else {
            return random.nextInt(2);
        }
    }

    public static int hasCar(User user) {
        if(user.getGender() == 0 && user.getAge() < 20) {
            return 0;
        } else if(user.getGender() == 1 && user.getAge() < 22) {
            return 0;
        } else {
            return random.nextInt(2);
        }
    }

    public static int hasHouse(User user) {
        if(user.getGender() == 0 && user.getAge() < 20) {
            return 0;
        } else if(user.getGender() == 1 && user.getAge() < 22) {
            return 0;
        } else {
            return random.nextInt(2);
        }
    }

    public static String[] brands = {
            "阿尔卡特", "三星", "夏普", "飞利浦", "中兴", "长虹", "LG", "诺基亚", "联想",
            "海信", "酷派", "华为", "荣耀", "TCL", "索尼", "苹果", "康佳", "小米", "魅族",
            "OPPO", "Vivo", "HTC", "一加", "传音"
    };
    public static String phoneBrand() {
        return brands[random.nextInt(brands.length)];
    }
}
