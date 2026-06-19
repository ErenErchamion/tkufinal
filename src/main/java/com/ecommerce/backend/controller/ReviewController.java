package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.ReviewRequest;
import com.ecommerce.backend.entity.Review;
import com.ecommerce.backend.service.ReviewService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ReviewController {

    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{productId}/reviews")
    public ResponseEntity<Review> addReview(
            @PathVariable Long productId,
            @Valid @RequestBody ReviewRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        
        String userId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        log.info("Received review request for Product ID: {} from User: {} ({})", productId, userId, email);
        
        Review review = reviewService.addReview(productId, request, userId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    @GetMapping("/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long productId) {
        log.info("Fetching reviews for Product ID: {}", productId);
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }
}
