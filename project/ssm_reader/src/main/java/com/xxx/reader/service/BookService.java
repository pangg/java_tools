package com.xxx.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.reader.entity.Book;

/**
 * 图书服务
 */
public interface BookService {
    /**
     * 分页查询图书
     * @param page 页号
     * @param rows 每行记录数
     * @return
     */
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows);
}
