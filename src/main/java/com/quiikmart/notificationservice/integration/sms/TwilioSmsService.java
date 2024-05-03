package com.quiikmart.notificationservice.integration.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService implements SmsService{
    private final String accountSid;
    private final String authToken;
    private final String fromNumber;

    public TwilioSmsService(@Value("${twilio.account.sid}") String accountSid,
                            @Value("${twilio.auth.token}") String authToken,
                            @Value("${twilio.from.number}") String fromNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromNumber = fromNumber;
        // Initialize Twilio with accountSid and authToken
    }

    @Override
    public void sendSms(String number, String message) {
        // Twilio Integration
    }
}
