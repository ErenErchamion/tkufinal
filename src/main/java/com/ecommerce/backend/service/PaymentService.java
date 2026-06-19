package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.PaymentRequest;
import com.ecommerce.backend.entity.Payment;
import com.ecommerce.backend.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment chargeCard(PaymentRequest request) {
        log.info("Processing payment for Order ID: {} with amount: {}", request.getOrderId(), request.getAmount());

        Payment payment = new Payment(
            null,
            request.getOrderId(),
            "N/A",
            request.getAmount(),
            "SUCCESS",
            LocalDateTime.now()
        );

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment processed successfully. Transaction ID: {}", savedPayment.getId());
        return savedPayment;
    }
}

