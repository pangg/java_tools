package com.xxx.mybatis.dao;

import com.xxx.mybatis.dto.GoodsDTO;
import com.xxx.mybatis.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsDAO {
    @Select("select * from t_goods where current_price between #{min} and #{max}\n" +
            "        order by current_price\n" +
            "        limit 0, #{limt}")
    public List<Goods> selectByPriceRange(@Param("min") Float min, @Param("max") Float max, @Param("limt") Integer limt);


    @Insert("insert into t_goods (goods_id, title, sub_title, original_cost, current_price, discount, is_free_delivery, category_id)\n" +
            "        values (#{goodsId}, #{title}, #{subTitle}, #{originalCost}, #{currentPrice}, #{discount}, #{isFreeDelivery}, #{categoryId});")
    @SelectKey(statement = "select last_insert_id()", before = false, keyProperty = "id", resultType = Integer.class)
    public int insert(Goods goods);


    @Select("select * from t_goods")
    @Results({
            @Result(column = "goods_id", property = "goods.goodsId"),
            @Result(column = "title", property = "goods.title")
    })
    public List<GoodsDTO> selectAll();

    @Select("select * from t_goods where category_id = #{cid}")
    public List<Goods> listByCategoryId(int cid);

    /**
     * 多对一关系查询
     */
    @Select("select * from t_goods")
    @Results({
            @Result(property = "category", column = "category_id", one = @One(select = "com.xxx.mybatis.dao.CategoryDAO.get"))
    })
    public List<Goods> list();

    @Select("select * from t_goods where goods_id = #{id}")
    public Goods getSingleGoods(int id);
}
