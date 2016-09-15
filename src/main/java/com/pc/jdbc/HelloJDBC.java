package com.pc.jdbc;

import java.io.*;
import java.sql.*;

/**
 * Created by switch on 16/8/26.
 */
public class HelloJDBC {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/cloud_study";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void helloWorld() throws ClassNotFoundException {
        // 1. 加载驱动
        Class.forName(JDBC_DRIVER);
        try {
            // 修改DB_URL使其支持使用游标
            DB_URL = DB_URL + "?useCursorFetch=true";
            // 2. 获取连接
            ct = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // 3. 执行SQL语句
            String sql = "SELECT userName FROM user";
            ps = ct.prepareStatement(sql);
            // 设置每次从数据库中获取的记录数n
            // 大于0则每次获取n条,n = 0则获取整个记录集
            ps.setFetchSize(1);
            rs = ps.executeQuery();
            // 4. 处理记录
            while (rs.next()) {
                System.out.println(rs.getString("userName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 释放资源
            try {
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

    public static void main(String[] args) throws ClassNotFoundException {
        helloWorld();
    }
}
