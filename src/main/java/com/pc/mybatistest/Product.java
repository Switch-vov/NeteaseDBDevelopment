package com.pc.mybatistest;

/**
 * Created by switch on 16/9/15.
 */
public class Product {
    private Integer id;
    private String productName;
    private String catalog;

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
