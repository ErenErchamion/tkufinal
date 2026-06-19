package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByProductIdOrderByCreatedAtDesc(Long productId);
    List<Question> findByAnswerTextIsNullOrderByCreatedAtDesc();
}
