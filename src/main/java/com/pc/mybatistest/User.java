package com.pc.mybatistest;

import java.util.List;

/**
 * Created by switch on 16/9/15.
 */
public class User {
    private Integer id;
    private String userName;
    private String tel;
    private List<Integer> products;

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }
}
