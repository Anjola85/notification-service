package com.quiikmart.notificationservice.notification.handler;

import com.quiikmart.notificationservice.dto.NotificationRequestDto;

public interface NotificationHandler {
    void handleNotification(NotificationRequestDto notificationRequest);
}
