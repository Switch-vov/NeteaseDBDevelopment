package com.pc.jdbc;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by switch on 16/8/29.
 */
public class JDBC4 {
    public final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/cloud_study";
    public final static String USER = "root";
    public final static String PASSWORD = "123456";

    private static BasicDataSource dataSource = null;

    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void dbPoolInit() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        dataSource.setInitialSize(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(100000);
        dataSource.setMaxIdle(2);
        dataSource.setMinIdle(2);

        dataSource.setTestWhileIdle(true);
        dataSource.setMinEvictableIdleTimeMillis(7 * 3600 * 1000);
        dataSource.setTimeBetweenEvictionRunsMillis(3600 * 1000 - 5);
    }

    public static void dbPoolTest() {
        try {
            ct = dataSource.getConnection();
            String sql = "select * from user";
            ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
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
