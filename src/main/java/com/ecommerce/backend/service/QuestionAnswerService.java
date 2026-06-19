package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.AnswerRequest;
import com.ecommerce.backend.dto.QuestionRequest;
import com.ecommerce.backend.entity.Question;
import com.ecommerce.backend.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QuestionAnswerService {

    private static final Logger log = LoggerFactory.getLogger(QuestionAnswerService.class);

    private final QuestionRepository questionRepository;

    public QuestionAnswerService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Transactional
    public Question askQuestion(Long productId, QuestionRequest request, String userId, String userEmail) {
        log.info("Adding question for Product ID: {} by User: {}", productId, userId);

        Question question = new Question(
            null,
            productId,
            userId,
            userEmail,
            request.getQuestionText(),
            null,
            LocalDateTime.now(),
            null
        );

        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByProductId(Long productId) {
        return questionRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }

    @Transactional
    public Optional<Question> answerQuestion(Long questionId, AnswerRequest request) {
        log.info("Answering Question ID: {}", questionId);

        return questionRepository.findById(questionId)
            .map(question -> {
                question.setAnswerText(request.getAnswerText());
                question.setAnsweredAt(LocalDateTime.now());
                return questionRepository.save(question);
            });
    }

    public List<Question> getUnansweredQuestions() {
        return questionRepository.findByAnswerTextIsNullOrderByCreatedAtDesc();
    }
}
