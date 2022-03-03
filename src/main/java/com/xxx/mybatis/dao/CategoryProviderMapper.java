package com.xxx.mybatis.dao;

import com.xxx.mybatis.dynaSql.CategoryDynaSqlProvider;
import com.xxx.mybatis.entity.Category;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface CategoryProviderMapper {
    @InsertProvider(type = CategoryDynaSqlProvider.class, method = "add")
    public int add(Category category);

    @DeleteProvider(type = CategoryDynaSqlProvider.class, method = "delete")
    public int delete(int id);

    @SelectProvider(type=CategoryDynaSqlProvider.class,method="get")
    public Category get(int id);

    @UpdateProvider(type=CategoryDynaSqlProvider.class,method="update")
    public int update(Category category);

    @SelectProvider(type=CategoryDynaSqlProvider.class,method="list")
    public List<Category> list();
}
