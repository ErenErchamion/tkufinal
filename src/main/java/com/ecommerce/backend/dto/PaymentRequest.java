package com.ecommerce.backend.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class PaymentRequest {
    @NotNull(message = "Payment amount cannot be null")
    @Positive(message = "Payment amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Order ID cannot be blank")
    private String orderId;

    public PaymentRequest() {}

    public PaymentRequest(BigDecimal amount, String orderId) {
        this.amount = amount;
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

