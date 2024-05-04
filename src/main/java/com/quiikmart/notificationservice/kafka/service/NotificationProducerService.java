package com.quiikmart.notificationservice.kafka.service;

public interface NotificationProducerService {
    void sendNotification(String topic, String message);
}
