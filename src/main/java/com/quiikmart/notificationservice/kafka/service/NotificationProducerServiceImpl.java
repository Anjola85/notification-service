package com.quiikmart.notificationservice.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducerServiceImpl implements NotificationProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public NotificationProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendNotification(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
