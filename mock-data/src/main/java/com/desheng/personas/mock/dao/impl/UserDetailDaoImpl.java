package com.desheng.personas.mock.dao.impl;

import com.desheng.personas.mock.dao.BaseDao;
import com.desheng.personas.mock.dao.UserDetailDao;
import com.desheng.personas.mock.pojo.UserDetail;
import com.desheng.personas.mock.util.DruidDBUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/*
    CREATE TABLE `pb_user_detail` (
      `detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '详情补充表的id',
      `userid` int(11) DEFAULT NULL COMMENT '用户id',
      `education` int(1) DEFAULT NULL COMMENT '学历：0未知，1初中及其以下，2高中，3中专，4大专，5本科，6研究生',
      `profession` varchar(20) DEFAULT NULL COMMENT '职业',
      `marriage` int(1) DEFAULT NULL COMMENT '婚姻状况：1未婚，2已婚，3离异',
      `has_children` int(11) DEFAULT NULL COMMENT '是否有小孩，0无小孩，1有小孩',
      `has_car` int(11) DEFAULT NULL COMMENT '是否有车：0无车，1有车',
      `has_house` int(11) DEFAULT NULL COMMENT '是否有房：0无房，1有房',
      `phone_brand` varchar(20) NOT NULL COMMENT '手机品牌',
      PRIMARY KEY (`detail_id`),
      KEY `user_id_fk` (`userid`),
      CONSTRAINT `user_id_fk` FOREIGN KEY (`userid`) REFERENCES `pb_user` (`userid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
public class UserDetailDaoImpl implements UserDetailDao {

    private QueryRunner qr = new QueryRunner(DruidDBUtil.getDataSource());

    protected String insertSQL = "insert into pb_user_detail (detail_id, userid, education, profession, marriage, has_children, has_car, has_house, phone_brand) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    @Override
    public void insert(UserDetail userDetail) {
        try {
            qr.update(insertSQL, userDetail.getDetailId(), userDetail.getUserid(),
                    userDetail.getEducation(), userDetail.getProfession(), userDetail.getMarriage(),
                    userDetail.getHasChildren(), userDetail.getHasCar(),
                    userDetail.getHasHouse(), userDetail.getPhoneBrand());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertBatch(List<UserDetail> list) {
        try {
            Object[][] params = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                UserDetail userDetail = list.get(i);
                Object[] obj = {
                        userDetail.getDetailId(),
                        userDetail.getUserid(),
                        userDetail.getEducation(),
                        userDetail.getProfession(),
                        userDetail.getMarriage(),
                        userDetail.getHasChildren(),
                        userDetail.getHasCar(),
                        userDetail.getHasHouse(),
                        userDetail.getPhoneBrand()
                };
                params[i] = obj;
            }
            qr.batch(insertSQL, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
