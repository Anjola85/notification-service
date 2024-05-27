package com.quiikmart.notificationservice.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public interface NotificationConsumerService {
    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
    void processMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment);
}
