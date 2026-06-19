package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.AnswerRequest;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.entity.Question;
import com.ecommerce.backend.service.ProductService;
import com.ecommerce.backend.service.QuestionAnswerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final ProductService productService;
    private final QuestionAnswerService questionAnswerService;

    public AdminController(ProductService productService, QuestionAnswerService questionAnswerService) {
        this.productService = productService;
        this.questionAnswerService = questionAnswerService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        log.info("Admin request: Creating product {}", product.getName());
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/products/{productId}/stock")
    public ResponseEntity<Product> updateStock(
            @PathVariable Long productId,
            @RequestParam Integer stockCount) {
        log.info("Admin request: Updating stock for Product ID: {} to {}", productId, stockCount);
        return productService.updateStock(productId, stockCount)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/questions/{questionId}/answer")
    public ResponseEntity<Question> answerQuestion(
            @PathVariable Long questionId,
            @Valid @RequestBody AnswerRequest request) {
        log.info("Admin request: Answering Question ID: {}", questionId);
        return questionAnswerService.answerQuestion(questionId, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/questions/unanswered")
    public ResponseEntity<List<Question>> getUnansweredQuestions() {
        log.info("Admin request: Fetching unanswered questions");
        return ResponseEntity.ok(questionAnswerService.getUnansweredQuestions());
    }
}
