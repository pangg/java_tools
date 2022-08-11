package com.xxx.sboot_mall.service;

import com.xxx.sboot_mall.model.vo.CartVo;

import java.util.List;

public interface CartService {
    List<CartVo> add(Integer userId, Integer productId, Integer count);
}
