package com.xxx.sboot_mall.controller;

import com.github.pagehelper.PageInfo;
import com.xxx.sboot_mall.common.ApiRestResponse;
import com.xxx.sboot_mall.model.pojo.Product;
import com.xxx.sboot_mall.model.request.ProductListReq;
import com.xxx.sboot_mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation("商品详情")
    @GetMapping("product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {
        Product product = productService.detail(id);
        return  ApiRestResponse.success(product);
    }

    @ApiOperation("客户端商品列表")
    @GetMapping("product/list")
    public ApiRestResponse list(ProductListReq productListReq) {
        PageInfo<Product> list = productService.list(productListReq);
        return  ApiRestResponse.success(list);
    }
}
