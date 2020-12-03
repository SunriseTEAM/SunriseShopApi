package me.zhulin.shopapi.api;


import me.zhulin.shopapi.entity.OrderMain;
import me.zhulin.shopapi.entity.ProductCategory;
import me.zhulin.shopapi.entity.ProductInfo;
import me.zhulin.shopapi.service.CategoryService;
import me.zhulin.shopapi.service.ProductService;
import me.zhulin.shopapi.vo.response.CategoryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/categoryList")
    public Page<ProductCategory> orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "3") Integer size,
                                     Authentication authentication) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductCategory> categoryPage;

            categoryPage = categoryService.findAll(request);

        return categoryPage;
    }

    /**
     * Show products in category
     *
     * @param categoryType
     * @param page
     * @param size
     * @return
     */

    @GetMapping("/category/{type}")
    public CategoryPage showOne(@PathVariable("type") Integer categoryType,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "3") Integer size) {

        ProductCategory cat = categoryService.findByCategoryType(categoryType);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInCategory = productService.findAllInCategory(categoryType, request);
        var tmp = new CategoryPage("", productInCategory);
        tmp.setCategory(cat.getCategoryName());
        return tmp;
    }

    @PostMapping("/seller/category/new")
    public ResponseEntity<?> add(@RequestBody ProductCategory category) {
        try {
            ProductCategory returnedCategory = categoryService.save(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/seller/category/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") String productId,
                               @Valid @RequestBody ProductCategory category,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(category.getCategoryId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("/delete/category/{id}")
    public ResponseEntity delete(@PathVariable("id") String categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok().build();
    }

}
