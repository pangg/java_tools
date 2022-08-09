package com.xxx.sboot_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.sboot_mall.common.Constant;
import com.xxx.sboot_mall.exception.ImoocMallException;
import com.xxx.sboot_mall.exception.ImoocMallExceptionEnum;
import com.xxx.sboot_mall.model.dao.ProductMapper;
import com.xxx.sboot_mall.model.pojo.Product;
import com.xxx.sboot_mall.model.query.ProductListQuery;
import com.xxx.sboot_mall.model.request.AddProductReq;
import com.xxx.sboot_mall.model.request.ProductListReq;
import com.xxx.sboot_mall.model.vo.CategoryVo;
import com.xxx.sboot_mall.service.CategoryService;
import com.xxx.sboot_mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品服务实现
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryService categoryService;

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

    @Override
    public void update(Product updateProduct) {
        Product productOld = productMapper.selectByName(updateProduct.getName());
        // 同名切id不通，不能继续更新
        if (productOld != null && !productOld.getId().equals(updateProduct.getId())) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }

        int count = productMapper.updateByPrimaryKeySelective(updateProduct);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        Product productOld = productMapper.selectByPrimaryKey(id);
        if (productOld == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }
        int count = productMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer sellStatus) {
        productMapper.batchUpdateSellStatus(ids, sellStatus);
    }

    @Override
    public PageInfo<Product> listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectListForAdmin();
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @Override
    public Product detail(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    @Override
    public PageInfo<Product> list(ProductListReq productListReq) {
        // 构建Query对象
        ProductListQuery productListQuery = new ProductListQuery();

        // 搜索处理
        if (!StringUtils.isEmpty(productListReq.getKeyword())) {
            String keyword = new StringBuilder().append("%").append(productListReq.getKeyword()).append("%")
                    .toString();
            productListQuery.setKeyword(keyword);
        }

        // 类目处理：包含选择类目的所有子类目
        if (productListReq.getCategoryId() != null) {
            List<CategoryVo> categoryVoList = categoryService.listCategoryForCustomer(productListReq.getCategoryId());
            ArrayList<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(productListReq.getCategoryId());
            getCategoryIds(categoryVoList, categoryIds);
            productListQuery.setCategoryIds(categoryIds);
        }

        // 排序
        String orderBy = productListReq.getOrderBy();
        if (Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
            PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize(), orderBy);
        } else {
            PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize());
        }

        List<Product> productList = productMapper.selectList(productListQuery);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;

    }

    private void getCategoryIds(List<CategoryVo> categoryVoList, ArrayList<Integer> categoryIds) {
        for (int i = 0; i < categoryVoList.size(); i++) {
            CategoryVo categoryVo = categoryVoList.get(i);
            if (categoryVo != null) {
                categoryIds.add(categoryVo.getId());
                if (categoryVo.getChildCategory() != null) {
                    getCategoryIds(categoryVo.getChildCategory(), categoryIds);
                }
            }
        }
    }
}
