package com.pc.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by switch on 16/8/29.
 */
public class JDBC5 {
    public final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/cloud_study";
    public final static String USER = "root";
    public final static String PASSWORD = "123456";

    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    private static ComboPooledDataSource dataSource = null;

    private static void dbPoolInit() {
        try {
            dataSource = new ComboPooledDataSource();
            // 设置基本信息
            dataSource.setDriverClass(DRIVER_NAME);
            dataSource.setJdbcUrl(DB_URL);
            dataSource.setUser(USER);
            dataSource.setPassword(PASSWORD);

            // 设置初始化连接数量
            dataSource.setInitialPoolSize(10);
            // 设置最大连接数量
            dataSource.setMaxPoolSize(20);
            // 设置最小连接数量
            dataSource.setMinPoolSize(5);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private static void dbPoolTest() {
        try {
            ct = dataSource.getConnection();
            String sql = "select * from user";
            ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("userName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public static void main(String[] args) {
        dbPoolInit();
        dbPoolTest();
    }

}
