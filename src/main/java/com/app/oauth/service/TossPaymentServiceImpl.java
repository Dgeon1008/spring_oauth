package com.app.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TossPaymentServiceImpl implements TossPaymentService {

    @Value("${toss.payments.api.key}")
    private String apiKey;

    @Value("${toss.payments.api.url}")
    private String apiUrl;

    @Override
    public String processPayment(Map<String, Object> paymentData) {

        RestTemplate restTemplate = new RestTemplate();

//        API키를 Base64로 인코딩
        String encodedApiKey = Base64.getEncoder().encodeToString((apiKey + ":").getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedApiKey);
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Accept", "application/json; charset=utf-8");

//        HTTP 바디에 결제 데이터 추가
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(paymentData, headers);
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
        try {
            Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), Map.class);
            log.info("주문자 : {}", responseMap.get("orderName"));
            log.info("카드 번호 : {}", ((Map<String, Object>)responseMap.get("card")).get("number"));
            log.info("responseMap {}", responseMap);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return response.toString();
    }
}














