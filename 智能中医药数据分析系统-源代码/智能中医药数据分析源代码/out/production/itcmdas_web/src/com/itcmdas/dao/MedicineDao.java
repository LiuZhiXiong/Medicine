package com.itcmdas.dao;

import com.itcmdas.util.DBUtil;
import com.itcmdas.vo.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Classname MedicineDao
 * @Description 对 itcmdas 数据库中的 medicine 表操作
 * @Author liubo
 * @Date 2020/10/8 16:12
 * @Version 1.0
 */
public class MedicineDao {
    public Medicine getMedicineByMedicineName(String medicineName) throws SQLException {
        //获取数据库连接
        Connection connection= DBUtil.getConnection();

        //sql语句
        String sql="select * from chinese_medicine where medicineName=?";

        //创建PreparedStatement 语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //PreparedStatement 语句赋值
        preparedStatement.setString(1,medicineName);

        //结果集
        ResultSet resultSet=preparedStatement.executeQuery();

        //将结果封装到Medicine对象中
        if(resultSet.next()){
            Medicine medicine=new Medicine();

            medicine.setId(resultSet.getInt("id"));
            medicine.setMedicineName(resultSet.getString("medicineName"));
            medicine.setEfficacy(resultSet.getString("efficacy"));
            medicine.setProperties(resultSet.getString("properties"));

            return  medicine;

        }else{
            return  null;
        }
    }
}
