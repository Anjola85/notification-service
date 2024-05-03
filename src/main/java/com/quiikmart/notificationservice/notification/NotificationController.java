package com.quiikmart.notificationservice.notification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @GetMapping("/notify")
    public String sendNotification() {
        // logic to send notification
        return "Notification sent!";
    }
}
