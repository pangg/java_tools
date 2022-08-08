package com.xxx.sboot_mall.controller;

import com.xxx.sboot_mall.common.ApiRestResponse;
import com.xxx.sboot_mall.model.pojo.Product;
import com.xxx.sboot_mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation("商品详情")
    @GetMapping("product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {
        Product product = productService.detail(id);
        return  ApiRestResponse.success(product);
    }
}
