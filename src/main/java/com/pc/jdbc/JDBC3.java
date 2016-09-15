package com.pc.jdbc;

import java.sql.*;

/**
 * Created by switch on 16/8/28.
 */
public class JDBC3 {
    // 静态常量
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/cloud_study";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    // JDBC接口
    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void main(String[] args) {
        try {
            // 1. 加载驱动
            Class.forName(JDBC_DRIVER);

            // 2. 创建连接
            ct = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // 创建SQL语句
            String sql = "SELECT * FROM product WHERE Id = '1'";

            // 3. 获取PreparedStatement对象,并执行
            ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();

            // 4. 处理结果集
            while (rs.next()) {
                System.out.println("商品名称是:" + rs.getString("ProductName") + " 库存数量是:" + rs.getString("Inventory"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
