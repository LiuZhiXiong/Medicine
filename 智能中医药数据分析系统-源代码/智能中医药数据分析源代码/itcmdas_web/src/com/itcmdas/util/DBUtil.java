package com.itcmdas.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Classname DBUtil
 * @Description 数据库工具类（将连接数据库方法封装）
 * @Author liubo
 * @Date 2020/10/8 15:45
 * @Version 1.0
 */
public class DBUtil {
    /**
     *连接数据库，获取连接对象
     * @return
     */
    public static Connection getConnection(){
        try {
            //加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            //连接数据库字符串
            String dbUrl="jdbc:mysql://115.29.204.107:3306/itcmdas?characterEncoding=utf8";

            //数据库角色&密码
            String user="itcmdas";
            String password="123456";

            //创建Connection对象
            Connection connection= DriverManager.getConnection(dbUrl,user,password);

            return  connection;

        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
