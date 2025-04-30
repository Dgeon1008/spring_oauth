package com.app.oauth.service;

import java.util.Map;

public interface TossPaymentService {
    String processPayment(Map<String, Object> paymentData);
}
