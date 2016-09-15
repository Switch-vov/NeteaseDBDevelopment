package com.pc.jdbc;

import java.sql.*;

/**
 * Created by switch on 16/8/30.
 */
public class JDBC9 {
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/cloud_study?useUnicode=true&characterEncoding=utf8";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static Connection ct = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    public static void main(String[] args) {
        try {
            // 1. 加载驱动
            Class.forName(DRIVER_NAME);
            // 2. 获取连接
            ct = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // 开启事务
            ct.setAutoCommit(false);

            // 3. 预处理并执行SQL
            // 订单表插入一条数据
            String sql = "INSERT INTO `Order`(buyer, ProductName) VALUES ('XiaoMing', 'bag')";
            ps = ct.prepareStatement(sql);
            ps.execute();

            // 库存表bag对应库存-1,这里并不考虑小于0
            String sql2 = "UPDATE Inventory SET Inventory = Inventory - 1 WHERE ProductName = 'bag'";
            ps = ct.prepareStatement(sql2);
            ps.executeUpdate();

            // 提交
            ct.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            // 回滚事务
            try {
                if (ct != null) {
                    ct.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
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
