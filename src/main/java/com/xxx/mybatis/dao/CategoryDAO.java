package com.xxx.mybatis.dao;

import com.xxx.mybatis.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryDAO {
    @Insert("insert into t_category (category_id, category_name, parent_id, category_level, category_order) " +
            "values (#{categoryId}, #{categoryName}, #{parentId}, #{categoryLevel}, #{categoryOrder}) ")
    public int add(Category category);

    @Delete(" delete from t_category where category_id = #{cid} ")
    public int delete(int cid);

    @Select(" select * from t_category where category_id = #{cid} ")
    public Category get(int cid);

    @Update(" update t_category set category_name = #{categoryName} where category_id = #{categoryId} ")
    public int update(Category category);

    @Select(" select * from t_category ")
    public List<Category> list();

    /**
     * 一对多关系
     */
    @Select(" select * from t_category where category_id = 1 ")
    @Results({
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "goods", javaType = List.class, column = "category_id", many =
                @Many(select = "com.xxx.mybatis.dao.GoodsDAO.listByCategoryId"))
    })
    public List<Category> listCategory();
}
