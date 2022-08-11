package com.xxx.sboot_mall.model.dao;

import com.xxx.sboot_mall.model.pojo.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);
}