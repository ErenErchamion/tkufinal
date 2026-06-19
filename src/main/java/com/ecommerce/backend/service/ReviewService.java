package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.ReviewRequest;
import com.ecommerce.backend.entity.Review;
import com.ecommerce.backend.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReviewService {

    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review addReview(Long productId, ReviewRequest request, String userId, String userEmail) {
        log.info("Adding review for Product ID: {} by User: {}", productId, userId);

        Review review = new Review(
            null,
            productId,
            userId,
            userEmail,
            request.getRating(),
            request.getComment(),
            LocalDateTime.now()
        );

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }
}
