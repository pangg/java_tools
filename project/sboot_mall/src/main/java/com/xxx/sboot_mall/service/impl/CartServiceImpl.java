package com.xxx.sboot_mall.service.impl;

import com.xxx.sboot_mall.common.Constant;
import com.xxx.sboot_mall.exception.ImoocMallException;
import com.xxx.sboot_mall.exception.ImoocMallExceptionEnum;
import com.xxx.sboot_mall.model.dao.CartMapper;
import com.xxx.sboot_mall.model.dao.ProductMapper;
import com.xxx.sboot_mall.model.pojo.Cart;
import com.xxx.sboot_mall.model.pojo.Product;
import com.xxx.sboot_mall.model.vo.CartVo;
import com.xxx.sboot_mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;

    @Override
    public List<CartVo> add(Integer userId, Integer productId, Integer count) {
        validProduct(productId, count);

        Cart cart = cartMapper.selectCartByUserAndProductId(userId, productId);
        if (cart == null) {
            //这个商品之前不在购物车
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setQuantity(count);
            cart.setSelected(Constant.Cart.CHECKED);
            cartMapper.insertSelective(cart);
        } else {
            // 商品已在购物车
            count = cart.getQuantity() + count;
            Cart cartNew = new Cart();
            cartNew.setQuantity(count);
            cartNew.setId(cart.getId());
            cartNew.setProductId(cart.getProductId());
            cartNew.setUserId(cart.getUserId());
            cartNew.setSelected(Constant.Cart.CHECKED);
            cartMapper.updateByPrimaryKeySelective(cartNew);
        }
        return null;
    }

    /**
     * 校验商品
     * @param productId
     * @param count
     */
    private void validProduct(Integer productId, Integer count) {
        Product product = productMapper.selectByPrimaryKey(productId);
        System.out.println(product);
        System.out.println(product.getStatus().equals(Constant.SaleStatue.NOT_SALE));
        // 判断商品是否存在，商品是否上架
        if (product == null || product.getStatus().equals(Constant.SaleStatue.NOT_SALE)) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_SALE);
        }

        // 判断商品库存
        if (count > product.getStock()) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_ENOUGH);
        }
    }

}
