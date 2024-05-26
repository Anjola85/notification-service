package com.quiikmart.notificationservice.notification.handler;

import com.quiikmart.notificationservice.dto.NotificationRequestDto;
import com.quiikmart.notificationservice.notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NotificationHandlerFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public NotificationHandler getHandler(NotificationRequestDto notificationRequest) {
        switch (NotificationType.valueOf(notificationRequest.getType().toUpperCase())) {
            case PROMOTIONAL:
                return null;
            case OTP:
                return applicationContext.getBean(OtpNotificationHandler.class);
            default:
                throw new UnsupportedOperationException("Unsupported notification type: " + notificationRequest.getType());
        }
    }
}
