package com.pc.jdbc;

import java.sql.*;

/**
 * Created by switch on 16/8/29.
 */
public class JDBC7 {
    public final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/cloud_study";
    public final static String USER = "root";
    public final static String PASSWORD = "123456";

    public static void getStudent(String name) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            ps = conn.prepareStatement("select name,score from student where name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name") + ":" + rs.getInt("score"));
            }
        } catch (SQLException e) {
            // ignore
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    // ignore
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    // ignore
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            getStudent("XiaoMing' or 1 = '1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
