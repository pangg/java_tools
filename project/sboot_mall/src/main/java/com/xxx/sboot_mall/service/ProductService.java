package com.xxx.sboot_mall.service;

import com.xxx.sboot_mall.model.pojo.Product;
import com.xxx.sboot_mall.model.request.AddProductReq;

import java.util.List;

public interface ProductService {

    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);
}
