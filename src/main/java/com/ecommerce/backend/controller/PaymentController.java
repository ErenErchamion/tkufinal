package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.PaymentRequest;
import com.ecommerce.backend.entity.Payment;
import com.ecommerce.backend.service.PaymentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/charge")
    public ResponseEntity<Payment> chargeCard(@Valid @RequestBody PaymentRequest request) {
        log.info("Received charge request for Order: {} with amount: {}", request.getOrderId(), request.getAmount());
        Payment payment = paymentService.chargeCard(request);
        return ResponseEntity.ok(payment);
    }
}
