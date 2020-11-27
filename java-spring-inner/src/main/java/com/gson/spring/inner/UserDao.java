package com.gson.spring.inner;

import java.io.Serializable;

public class UserDao extends BasicDao implements Serializable {

    private static final long serialVersionUID = -6632133645869858646L;
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void init(){
        System.out.println("----init-----");
    }
    @Override
    public String toString() {
        return "UserDao{" +
                "tableName='" + tableName + '\'' +
                '}' + super.toString();
    }
}
