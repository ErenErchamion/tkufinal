package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(Long categoryId, Long brandId) {
        if (categoryId != null && brandId != null) {
            return productRepository.findByCategoryIdAndBrandId(categoryId, brandId);
        } else if (categoryId != null) {
            return productRepository.findByCategoryId(categoryId);
        } else if (brandId != null) {
            return productRepository.findByBrandId(brandId);
        } else {
            return productRepository.findAll();
        }
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Optional<Product> updateStock(Long productId, Integer stockCount) {
        return productRepository.findById(productId)
            .map(product -> {
                product.setStockCount(stockCount);
                return productRepository.save(product);
            });
    }
}
