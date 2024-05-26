package com.quiikmart.notificationservice.notification.handler;

import com.quiikmart.notificationservice.integration.email.EmailService;
import com.quiikmart.notificationservice.integration.sms.SmsService;
import com.quiikmart.notificationservice.notification.Notification;
import com.quiikmart.notificationservice.notification.NotificationChannel;
import com.quiikmart.notificationservice.otp.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.quiikmart.notificationservice.notification.NotificationChannel.EMAIL;

@Service
public class OtpNotificationHandler implements NotificationHandler {
    private static final Logger logger = LoggerFactory.getLogger(OtpNotificationHandler.class);

    @Autowired
    private OtpService otpService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;

    @Override
    public void handleNotification(Notification notification) {
        try {
            // need to pass userId to generate otp here
            String userId = "1";
            int otpCode = otpService.generateOtp(userId);

            if(EMAIL.name().equalsIgnoreCase(notification.getChannel())) {
                // TODO: map NotificationRequest here
                //TODO: generate otp content here

                emailService.sendEmail(notification.getRecipient(), notification.getSubject(), notification.getContent());
            } else if(NotificationChannel.MOBILE.name().equalsIgnoreCase(notification.getChannel())) {
                //TODO:  generate otp content here
                smsService.sendSms(notification.getRecipient(), notification.getContent());
            }
        } catch (Exception e) {
            logger.error("Error occurred when handling notification with error: {}", e.getMessage());
            throw new RuntimeException("Error encountered while handling notification");
        }
    }
}
