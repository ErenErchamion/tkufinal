package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.QuestionRequest;
import com.ecommerce.backend.entity.Question;
import com.ecommerce.backend.service.QuestionAnswerService;
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
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionAnswerService questionAnswerService;

    public QuestionController(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    @PostMapping("/{productId}/questions")
    public ResponseEntity<Question> askQuestion(
            @PathVariable Long productId,
            @Valid @RequestBody QuestionRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        
        String userId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        log.info("Received question request for Product ID: {} from User: {} ({})", productId, userId, email);

        Question question = questionAnswerService.askQuestion(productId, request, userId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping("/{productId}/questions")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long productId) {
        log.info("Fetching questions for Product ID: {}", productId);
        List<Question> questions = questionAnswerService.getQuestionsByProductId(productId);
        return ResponseEntity.ok(questions);
    }
}
