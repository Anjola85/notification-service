package com.quiikmart.notificationservice.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void  sendNotification(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
