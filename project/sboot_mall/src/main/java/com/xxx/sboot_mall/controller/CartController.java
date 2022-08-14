package com.xxx.sboot_mall.controller;

import com.xxx.sboot_mall.common.ApiRestResponse;
import com.xxx.sboot_mall.filter.UserFilter;
import com.xxx.sboot_mall.model.vo.CartVo;
import com.xxx.sboot_mall.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @ApiOperation("购物车列表")
    @GetMapping("/list")
    public ApiRestResponse list() {
        List<CartVo> cartVos = cartService.list(UserFilter.currentUser.getId());
        return ApiRestResponse.success(cartVos);
    }

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVo> cartVos = cartService.add(UserFilter.currentUser.getId(), productId, count);
        return ApiRestResponse.success(cartVos);
    }

    @ApiOperation("更新购物车")
    @PostMapping("/update")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVo> cartVos = cartService.update(UserFilter.currentUser.getId(), productId, count);
        return ApiRestResponse.success(cartVos);
    }

    @ApiOperation("删除购物车商品")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam Integer productId) {
        List<CartVo> cartVos = cartService.delete(UserFilter.currentUser.getId(), productId);
        return ApiRestResponse.success(cartVos);
    }

    @ApiOperation("选中/取消购物车商品")
    @PostMapping("/select")
    public ApiRestResponse select(@RequestParam Integer productId, @Param("selected") Integer selected) {
        List<CartVo> cartVos = cartService.selectOrNot(UserFilter.currentUser.getId(), productId, selected);
        return ApiRestResponse.success(cartVos);
    }

    @ApiOperation("全选/全取消购物车商品")
    @PostMapping("/selectAll")
    public ApiRestResponse selectAll(@Param("selected") Integer selected) {
        List<CartVo> cartVos = cartService.selectAllOrNot(UserFilter.currentUser.getId(), selected);
        return ApiRestResponse.success(cartVos);
    }
}
