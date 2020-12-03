package me.zhulin.shopapi.service;

import me.zhulin.shopapi.entity.OrderMain;
import me.zhulin.shopapi.entity.ProductCategory;
import me.zhulin.shopapi.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

//    List<ProductCategory> findAll();
//
Page<ProductCategory> findAll(Pageable pageable);


    ProductCategory findOne(String categoryId);

    ProductCategory findByCategoryType(Integer categoryType);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory update(ProductCategory category);

    ProductCategory save(ProductCategory category);

    void delete(String categoryId);


}
