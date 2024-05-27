package com.quiikmart.notificationservice.auth;

import com.quiikmart.notificationservice.otp.Otp;

public interface AuthService {
    void saveOtp(String userId, Otp otp);
}
