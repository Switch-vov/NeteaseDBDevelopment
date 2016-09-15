package com.pc.jdbc;

import java.sql.*;

/**
 * Created by switch on 16/8/29.
 * 该案例演示了JDBC中保存点(Savepoint)的使用
 * 通过使用保存点,事务可以做到当超过保存点的语句发生异常时
 * 回退到保存点,再执行其他语句
 */
public class JDBC8 {
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/cloud_study?useUnicode=true&characterEncoding=UTF8";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static Connection ct = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static Savepoint sp = null;

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER_NAME);
            ct = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // 自动提交关闭
            // 事务处理
            ct.setAutoCommit(false);
            String sql = "update product set inventory = ? where productName = ?";
            ps = ct.prepareStatement(sql);
            // 将面包的库存设置为5
            ps.setInt(1, 5);
            ps.setString(2, "bread");
            ps.execute();
            // 设置保存点
            sp = ct.setSavepoint();

            // 将苹果的库存设置为10
            ps.setInt(1, 10);
            ps.setString(2, "apple");
            ps.execute();
            // 抛出异常,
            throw new SQLException();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            if (ct != null) {
                try {
                    // 回滚到保存点
                    ct.rollback(sp);
                    // 将牛奶的库存设置为10
                    ps.setInt(1, 10);
                    ps.setString(2, "milk");
                    ps.execute();
                    ct.commit();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            // 5. 关闭资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (ct == null) {
                    ct.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
