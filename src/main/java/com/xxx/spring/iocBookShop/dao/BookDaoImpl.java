package com.xxx.spring.iocBookShop.dao;

public class BookDaoImpl implements BookDao{
    @Override
    public void insert() {
        System.out.println("向Mysql Book表中插入一条数据");
    }
}
