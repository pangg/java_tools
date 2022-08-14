package com.xxx.sboot_mall.service;

import com.xxx.sboot_mall.model.vo.CartVo;

import java.util.List;

public interface CartService {
    List<CartVo> list(Integer userId);

    List<CartVo> add(Integer userId, Integer productId, Integer count);

    List<CartVo> update(Integer userId, Integer productId, Integer count);

    List<CartVo> delete(Integer userId, Integer productId);

    List<CartVo> selectOrNot(Integer userId, Integer productId, Integer selected);

    List<CartVo> selectAllOrNot(Integer userId, Integer selected);
}
