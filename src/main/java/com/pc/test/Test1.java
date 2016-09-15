package com.pc.test;

import java.sql.*;

/**
 * Created by switch on 16/9/15.
 */
public class Test1 {
    // 常量
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/cloud_study?useUnicode=true&characterEncoding=utf8";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "123456";

    // JDBC接口
    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void main(String[] args) {

        try {
            // 1. 加载驱动
            Class.forName(DRIVER_NAME);
            // 2. 获取连接
            ct = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            // 3. 获取PreparedStatement对象并注入参数
            String sql = "select * from userCourse where userName = ?";
            ps = ct.prepareStatement(sql);
            ps.setString(1, "ZhangSan");
            // 4. 执行查询,并处理结果
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("课程名为:" + rs.getString("courseName"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 5. 关闭连接
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (ct != null) {
                    ct.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
