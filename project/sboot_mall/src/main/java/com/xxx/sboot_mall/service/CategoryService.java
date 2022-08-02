package com.xxx.sboot_mall.service;

import com.github.pagehelper.PageInfo;
import com.xxx.sboot_mall.model.pojo.Category;
import com.xxx.sboot_mall.model.request.AddCategoryRequest;

public interface CategoryService {
    void add(AddCategoryRequest addCategoryRequest);

    void update(Category updateCategory);

    void delete(Integer id);

    PageInfo<Category> listForAdmin(Integer pageNum, Integer pageSize);
}
