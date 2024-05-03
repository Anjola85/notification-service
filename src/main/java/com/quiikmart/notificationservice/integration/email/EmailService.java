package com.quiikmart.notificationservice.integration.email;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
