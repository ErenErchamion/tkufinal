package com.ecommerce.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class AnswerRequest {
    @NotBlank(message = "Answer text cannot be blank")
    private String answerText;

    public AnswerRequest() {}

    public AnswerRequest(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
