package com.xxx.sboot_mall.service.impl;

import com.xxx.sboot_mall.exception.ImoocMallException;
import com.xxx.sboot_mall.exception.ImoocMallExceptionEnum;
import com.xxx.sboot_mall.model.dao.ProductMapper;
import com.xxx.sboot_mall.model.pojo.Product;
import com.xxx.sboot_mall.model.request.AddProductReq;
import com.xxx.sboot_mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(AddProductReq addProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product productOld = productMapper.selectByName(addProductReq.getName());
        if (productOld != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.insertSelective(product);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.create_failed);
        }
    }
}
