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

    /**
     * 根据图书编号查询图书对象
     * @param bookId 图书编号
     * @return 图书对象
     */
    public Book selectById (Long bookId);

    /**
     * 更新图书评分/评价数量
     */
    public void updateEvaluation();

    /**
     * 创建新的图书
     * @param book 图书实体
     * @return
     */
    public Book createBook(Book book);

    /**
     * 更新图书
     * @param book 新图书数据
     * @return 更新后的图书数据
     */
    public Book updateBook(Book book);

    /**
     * 删除图书及相关信息
     * @param bookId 图书编号
     */
    public void deleteBook(Long bookId);
}
