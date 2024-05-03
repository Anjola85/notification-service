package com.quiikmart.notificationservice.integration.email;

import org.springframework.beans.factory.annotation.Value;

public class SendGridService implements EmailService {

    private final String apiKey;

    public SendGridService(@Value("${sendgrid.api.key}") String apiKey) {
        this.apiKey = apiKey;
        // Initialize SendGrid with apiKey
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        // SendGrid API integration code here
    }
}
