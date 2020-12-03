package me.zhulin.shopapi.service.impl;


import me.zhulin.shopapi.entity.OrderMain;
import me.zhulin.shopapi.entity.ProductCategory;
import me.zhulin.shopapi.entity.ProductInfo;
import me.zhulin.shopapi.enums.ResultEnum;
import me.zhulin.shopapi.exception.MyException;
import me.zhulin.shopapi.repository.ProductCategoryRepository;
import me.zhulin.shopapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    CategoryService categoryService;

    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return productCategoryRepository.findAllByOrderByCategoryType(pageable);
    }

    @Override
    public ProductCategory findOne(String categoryId) {

        ProductCategory category = productCategoryRepository.findByCategoryId(categoryId);
        return category;
    }


    @Override
    public ProductCategory findByCategoryType(Integer categoryType) {
        ProductCategory res = productCategoryRepository.findByCategoryType(categoryType);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> res = productCategoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
       //res.sort(Comparator.comparing(ProductCategory::getCategoryType));
        return res;
    }

    @Override
    public ProductCategory update(ProductCategory category) {

        // if null throw exception
        categoryService.findByCategoryType(category.getCategoryType());

        return productCategoryRepository.save(category);
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return update(category);
    }

    @Override
    public void delete(String categoryId) {
        ProductCategory category = findOne(categoryId);
        if (category == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        productCategoryRepository.delete(category);

    }


}
