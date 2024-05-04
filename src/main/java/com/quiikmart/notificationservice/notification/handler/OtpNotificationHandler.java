package com.quiikmart.notificationservice.notification.handler;

import com.quiikmart.notificationservice.integration.email.EmailService;
import com.quiikmart.notificationservice.integration.sms.SmsService;
import com.quiikmart.notificationservice.notification.Notification;
import com.quiikmart.notificationservice.notification.NotificationChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpNotificationHandler implements NotificationHandler {
    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Override
    public void handleNotification(Notification notification) {
        if(NotificationChannel.EMAIL.name().equalsIgnoreCase(notification.getChannel())) {
            emailService.sendEmail(notification.getRecipient(), notification.getSubject(), notification.getContent());
        } else if(NotificationChannel.MOBILE.name().equalsIgnoreCase(notification.getChannel())) {
            smsService.sendSms(notification.getRecipient(), notification.getContent());
        }
    }
}
