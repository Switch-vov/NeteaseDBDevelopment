package com.pc.mybatiscrud;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by switch on 16/9/9.
 * 测试MyBatis框架的CRUD操作
 */
public class HelloMyBatisMoreOp {
    public static void main(String[] args) {
        // 1. 声明配置文件
        String resource = "confCRUD.xml";

        // 2. 加载应用配置文件
        InputStream is = HelloMyBatisMoreOp.class.getClassLoader().getResourceAsStream(resource);

        // 3. 创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 4. 创建SqlSession对象
        SqlSession session = sessionFactory.openSession(true);

        try {
            // 5. 获取操作类
            UserOp userOp = session.getMapper(UserOp.class);

            // 6. 完成操作
            // 插入用户
            User user = new User("switch", "D公司");
            userOp.addUser(user);
            System.out.println(user.getId());

            // 查询用户
            user = userOp.getUser(user.getId());
            System.out.println("userId:" + user.getId() + ", userName:" + user.getUserName() + ", corp:" + user.getCorp());

            // 更新用户
            user.setUserName("SSSSSS");
            userOp.updateUser(user);

            // 删除用户
            userOp.deleteUser(user.getId());

        } finally {
            // 7. 关闭Session
        }


    }
}
