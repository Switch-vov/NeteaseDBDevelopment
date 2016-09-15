package com.pc.jdbc;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by switch on 16/8/29.
 */
public class JDBC6 {
    // 基本信息
    public final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/cloud_study?useUnicode=true&characterEncoding=UTF8";
    public final static String USER = "root";
    public final static String PASSWORD = "123456";

    // 数据源
    private static BasicDataSource dataSource = null;

    // JDBC接口
    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void dbPoolInit() {
        // 配置数据源基本信息
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        // 配置连接池连接信息
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(100000);
        dataSource.setMaxIdle(2);
        dataSource.setMinIdle(2);

        // 配置连接池定期检测信息
        dataSource.setTestWhileIdle(true);
        dataSource.setMinEvictableIdleTimeMillis(7 * 3600 * 1000);
        dataSource.setTimeBetweenEvictionRunsMillis(3600 * 1000 - 5);
    }

    public static void dbPoolTest() {
        try {
            // 通过数据源获取连接
            ct = dataSource.getConnection();
            // 创建SQL语句
            String sql = "SELECT * FROM product WHERE Id = '1'";

            // 3. 获取PreparedStatement对象,并执行
            ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();

            // 4. 处理结果集
            while (rs.next()) {
                System.out.println("商品名称是:" + rs.getString("ProductName") + " 库存数量是:" + rs.getString("Inventory"));
            }
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

    public static void main(String[] args) {
        dbPoolInit();
        dbPoolTest();
    }
}
