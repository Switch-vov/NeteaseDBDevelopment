package com.pc.test;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;

/**
 * Created by switch on 16/9/15.
 */
public class Test3 {
    // 常量
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/cloud_study?useUnicode=true&characterEncoding=utf8";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "123456";

    // JDBC接口
    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    // DBCP数据源
    private static BasicDataSource dataSource = null;

    // 初始化数据源对象
    public static void dataSourceInit() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        // 设置连接数信息
        dataSource.setInitialSize(20);
        dataSource.setMaxActive(50);
        dataSource.setMaxWait(60000);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(5);
    }

    public static void main(String[] args) {
        try {
            // 1. 加载驱动
            Class.forName(DRIVER_NAME);
            // 2. 获取连接
            dataSourceInit();
            ct = dataSource.getConnection();
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
