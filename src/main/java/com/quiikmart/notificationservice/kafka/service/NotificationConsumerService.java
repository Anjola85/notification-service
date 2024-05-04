package com.quiikmart.notificationservice.kafka.service;

public interface NotificationConsumerService {
    void processMessage(String message);
}
