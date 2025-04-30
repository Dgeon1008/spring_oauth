package com.app.oauth.controller;

import com.app.oauth.service.TossPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payments/*")
public class PaymentController {
    private final TossPaymentService tossPaymentService;

    @PostMapping("/toss")
    public ResponseEntity<String> processPayment(@RequestBody Map<String, Object> paymentData) {
        log.info("controller paymentData {}", paymentData);
        String response = tossPaymentService.processPayment(paymentData);
        return ResponseEntity.ok(response);
    }

}
