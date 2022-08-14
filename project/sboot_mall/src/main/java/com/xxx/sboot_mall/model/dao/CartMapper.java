package com.xxx.sboot_mall.model.dao;

import com.xxx.sboot_mall.model.pojo.Cart;
import com.xxx.sboot_mall.model.vo.CartVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    List<CartVo> selectList(@Param("userId") Integer userId);

    Integer selectOrNot(@Param("userId") Integer userId, @Param("productId") Integer productId,
                        @Param("selected") Integer selected);
}