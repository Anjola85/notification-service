package com.quiikmart.notificationservice.notification.handler;

import com.quiikmart.notificationservice.auth.AuthService;
import com.quiikmart.notificationservice.dto.NotificationRequestDto;
import com.quiikmart.notificationservice.integration.email.EmailService;
import com.quiikmart.notificationservice.integration.sms.SmsService;
import com.quiikmart.notificationservice.notification.Notification;
import com.quiikmart.notificationservice.notification.NotificationService;
import com.quiikmart.notificationservice.otp.Otp;
import com.quiikmart.notificationservice.otp.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.quiikmart.notificationservice.notification.NotificationChannel.EMAIL;
import static com.quiikmart.notificationservice.notification.NotificationChannel.MOBILE;

@Service
public class OtpNotificationHandler implements NotificationHandler {
    private static final Logger logger = LoggerFactory.getLogger(OtpNotificationHandler.class);
    @Autowired
    private OtpService otpService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private AuthService authService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public void handleNotification(NotificationRequestDto notificationReq) {
        try {
            String userId = notificationReq.getUserId();
            if (userId == null)
                throw new RuntimeException("missing user id in notification request");

            Otp otp = otpService.generateOtp();
            String content = notificationService.generateNotificationContent(otp);
            Notification notification = NotificationService.mapNotification(notificationReq, content);

            if (EMAIL.name().equalsIgnoreCase(notificationReq.getChannel())) {
                emailService.sendEmail(notification.getRecipient(), notification.getSubject(), notification.getContent());
                 authService.saveOtp(userId, otp);
            } else if (MOBILE.name().equalsIgnoreCase(notification.getChannel())) {
                smsService.sendSms(notification.getRecipient(), notification.getContent());
                authService.saveOtp(userId, otp);
            }
        } catch (Exception e) {
            logger.error("Error occurred when handling notification with error: {}", e.getMessage());

            if(e.getClass().equals(RuntimeException.class))
                throw (RuntimeException) e;

            throw new RuntimeException("Error encountered while handling notification");
        }
    }
}
