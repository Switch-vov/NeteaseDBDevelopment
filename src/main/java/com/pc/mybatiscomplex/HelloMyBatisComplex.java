package com.pc.mybatiscomplex;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by switch on 16/9/13.
 * 测试类
 */
public class HelloMyBatisComplex {
    public static void main(String[] args) {
        // 1. 声明配置文件的目录
        String resource = "confComplex.xml";

        // 2. 加载应用配置文件
        InputStream is = HelloMyBatisComplex.class.getClassLoader().getResourceAsStream(resource);

        // 3. 创建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 4. 获取Session————try-with-resource
        try (SqlSession session = sessionFactory.openSession()) {
            // 5. 获取操作类
            UserOp userOp = session.getMapper(UserOp.class);
            // 6. 完成查询操作
            User user = userOp.getUser(1);

            // 验证、输出结果
            // 用户信息
            System.out.println("--------用户信息--------");
            System.out.println("职员ID:" + user.getId() + " 职员名:" + user.getUserName() + " 公司名:" + user.getCorp());
            // 选课信息
            System.out.println("--------选课信息--------");
            for (Course course : user.getCourses()) {
                System.out.println("课程ID:" + course.getId() + " 课程名:" + course.getCourseName() +
                        " 讲师ID:" + course.getTeacher().getId() + " 讲师名:" + course.getTeacher().getTeacherName());
            }
        }
    }
}
