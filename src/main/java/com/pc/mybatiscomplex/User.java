package com.pc.mybatiscomplex;

import java.util.List;

/**
 * Created by switch on 16/9/13.
 * 职员Bean(POJO)————与user表映射
 */
public class User {
    private Integer id;
    private String userName;
    private String corp;
    private List<Course> courses;

    public User(Integer id, String userName, String corp) {
        this.id = id;
        this.userName = userName;
        this.corp = corp;
    }

    public User(String userName, String corp) {
        this.userName = userName;
        this.corp = corp;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
