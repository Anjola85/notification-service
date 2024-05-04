package com.quiikmart.notificationservice.integration.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendGridService implements EmailService {

    private final String apiKey;

    public SendGridService(@Value("${sendgrid.api.key}") String apiKey) {
        this.apiKey = apiKey;
        // TODO: Initialize SendGrid with apiKey
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        // TODO: SendGrid API integration code here
    }
}
