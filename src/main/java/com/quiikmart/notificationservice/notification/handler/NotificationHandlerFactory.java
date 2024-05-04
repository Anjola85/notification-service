package com.quiikmart.notificationservice.notification.handler;

import com.quiikmart.notificationservice.notification.Notification;
import com.quiikmart.notificationservice.notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NotificationHandlerFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public NotificationHandler getHandler(Notification notification) {
        switch (NotificationType.valueOf(notification.getType().toUpperCase())) {
            case OTP:
                return applicationContext.getBean(OtpNotificationHandler.class);
            default:
                throw new UnsupportedOperationException("Unsupported notification type: " + notification.getType());
        }
    }
}
