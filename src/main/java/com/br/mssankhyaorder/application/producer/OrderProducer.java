package com.br.mssankhyaorder.application.producer;

import com.br.mssankhyaorder.application.dto.OrderMessage;
import com.br.mssankhyaorder.domain.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProducer {

    @Value("${kafka.orders.topic}")
    private String ordersTopic;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    public void sendOrderMessage(OrderMessage message) {
        try {

            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(ordersTopic, jsonMessage);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao converter o objeto Order para JSON: {}", e.getMessage());
        }
    }
}
