package com.quiikmart.notificationservice.integration.sms;

public interface SmsService {
    void sendSms(String number, String message);
}
