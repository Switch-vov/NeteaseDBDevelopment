package com.pc.mybatisanno;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by switch on 16/9/7.
 * 测试注解实现mapper配置
 */
public class HelloMyBatisAnnotation {
    public static void main(String[] args) {
        // 1. 声明配置文件的目录
        String resource = "confAnnotation.xml";

        // 2. 加载应用配置文件
        InputStream is = HelloMyBatisAnnotation.class.getClassLoader().getResourceAsStream(resource);

        // 3. 创建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 4. 添加映射
        Configuration conf = sessionFactory.getConfiguration();
        conf.addMapper(GetUserInfoAnnotation.class);

        // 5. 获取Session
        SqlSession session = sessionFactory.openSession();
        try {

            // 6. 获取操作类
            GetUserInfoAnnotation getUserInfo = session.getMapper(GetUserInfoAnnotation.class);

            // 7. 完成查询操作
            User user = getUserInfo.getUser(1);
            System.out.println(user.getId() + " " + user.getUserName() + " " + user.getCorp());
        } finally {
            // 8. 关闭Session
            session.close();
        }
    }
}
