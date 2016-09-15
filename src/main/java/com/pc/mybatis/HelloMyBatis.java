package com.pc.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by switch on 16/8/30.
 * 测试MyBatis框架
 */
public class HelloMyBatis {
    public static void main(String[] args) {
        // 1.声明配置文件的目录
        String resource = "conf.xml";

        // 2. 加载应用配置文件
        InputStream is = HelloMyBatis.class
                .getClassLoader().getResourceAsStream(resource);

        // 3. 创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 4. 获取SqlSession
        SqlSession session = sessionFactory.openSession();

        try {
            // 5. 获取操作对象
            GetUserInfo getUserInfo = session.getMapper(GetUserInfo.class);

            // 6. 完成查询操作
            User user = getUserInfo.getUser(1);
            System.out.println(user.getId() + " " + user.getUserName() + " " + user.getCorp());
        } finally {
            // 7. 关闭session
            session.close();
        }

    }
}
