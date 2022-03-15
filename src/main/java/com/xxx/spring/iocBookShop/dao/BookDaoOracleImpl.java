package com.xxx.spring.iocBookShop.dao;

public class BookDaoOracleImpl implements BookDao{
    @Override
    public void insert() {
        System.out.println("向Oracle Book表插入一条数据");
    }
}
