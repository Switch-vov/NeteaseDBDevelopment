package com.pc.jdbc;

import java.sql.*;

/**
 * Created by switch on 16/8/28.
 */
public class JDBC2 {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/cloud_study";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    private static Connection ct = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            ct = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = ct.createStatement();

            // 将SQL语句添加到批处理列表
            stmt.addBatch("INSERT INTO user(userName, age) VALUES('zs', 20)");
            stmt.addBatch("INSERT INTO user(userName, age) VALUES('ls', 30)");
            stmt.addBatch("INSERT INTO user(userName, age) VALUES('ww', 25)");

            // 执行批处理列表中的SQL语句
            stmt.executeBatch();

            // 清除批处理列表中的SQL语句
            stmt.clearBatch();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
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
