package com.quiikmart.notificationservice.notification;

import com.quiikmart.notificationservice.dto.NotificationRequestDto;
import com.quiikmart.notificationservice.otp.Otp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void  sendNotification(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
    public static Notification mapNotification(NotificationRequestDto notificationReq, String content) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationReq, notification);
        notification.setSubject("Verify Your Account with This OTP");
        notification.setContent(content);
        return notification;
    }

    public String generateNotificationContent(Otp otp) {
        return String.format(
                "Use this code for verification:\n %d \nExpires in %d minutes.\nDO NOT SHARE THIS CODE.",
                otp.getCode(),
                otp.getExpiryTimeInMinutes()
        );
    }
}
