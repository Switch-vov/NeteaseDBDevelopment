package com.pc.mybatistest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by switch on 16/9/15.
 */
public class HelloMybatisTest {
    public static void main(String[] args) {
        String resource = "confTest.xml";
        InputStream is = HelloMybatisTest.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        try (SqlSession session = sessionFactory.openSession()) {
            Op op = session.getMapper(Op.class);
            User user = op.getUser(1);
            System.out.println(user.getId() + " " + user.getUserName() + " " + user.getTel());
            for (Integer product : user.getProducts()) {
                System.out.print(product + " ");
            }
            System.out.println();
            System.out.println("-----------------------");
            Product product = op.getProduct(2);
            System.out.println(product.getId() + " " + product.getProductName() + " " + product.getCatalog());
        }

    }
}
