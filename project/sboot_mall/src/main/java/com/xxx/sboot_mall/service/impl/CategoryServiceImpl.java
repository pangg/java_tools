package com.xxx.sboot_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.sboot_mall.exception.ImoocMallException;
import com.xxx.sboot_mall.exception.ImoocMallExceptionEnum;
import com.xxx.sboot_mall.model.dao.CategoryMapper;
import com.xxx.sboot_mall.model.pojo.Category;
import com.xxx.sboot_mall.model.request.AddCategoryRequest;
import com.xxx.sboot_mall.model.vo.CategoryVo;
import com.xxx.sboot_mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import springfox.documentation.annotations.Cacheable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryRequest addCategoryRequest) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryRequest, category);
        Category categoryOld = categoryMapper.selectByName(addCategoryRequest.getName());
        if (categoryOld != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        int count = categoryMapper.insertSelective(category);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.create_failed);
        }
    }

    @Override
    public void update(Category updateCategory) {
        if (updateCategory.getName() != null) {
            Category categoryOld = categoryMapper.selectByName(updateCategory.getName());
            if (categoryOld != null && categoryOld.getId() != updateCategory.getId()) {
                throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
            }
        }
        int count = categoryMapper.updateByPrimaryKeySelective(updateCategory);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        Category categoryOld = categoryMapper.selectByPrimaryKey(id);
        if (categoryOld == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }

        int count = categoryMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo<Category> listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "type, order_num");
        List<Category> categoryList = categoryMapper.selectList();
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        return pageInfo;
    }

    @Override
    // redis 缓存
    @Cacheable(value = "listCategoryForCustomer")
    public List<CategoryVo> listCategoryForCustomer(Integer parentId) {
        ArrayList<CategoryVo> categoryVos = new ArrayList<>();
        recursivelyFindCategories(categoryVos, parentId);
        return categoryVos;
    }

    private void recursivelyFindCategories(List<CategoryVo> categoryVoList, Integer parentId) {
        // 递归获取所有子类别，并组合成一个目录树
        List<Category> categoryList = categoryMapper.selectCategoriesByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVoList.add(categoryVo);
                recursivelyFindCategories(categoryVo.getChildCategory(), categoryVo.getId());
            }
        }
    }
}
