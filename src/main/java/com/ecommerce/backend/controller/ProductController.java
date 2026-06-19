package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Brand;
import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.service.BrandService;
import com.ecommerce.backend.service.CategoryService;
import com.ecommerce.backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public ProductController(ProductService productService, 
                             CategoryService categoryService, 
                             BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long brandId) {
        log.info("Fetching products with categoryId: {}, brandId: {}", categoryId, brandId);
        List<Product> products = productService.getAllProducts(categoryId, brandId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        log.info("Fetching product with ID: {}", id);
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        log.info("Fetching all categories");
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        log.info("Fetching all brands");
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        log.info("Fetching brand with ID: {}", id);
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
