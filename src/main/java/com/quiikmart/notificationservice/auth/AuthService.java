package com.quiikmart.notificationservice.auth;

public interface AuthService {
    void saveOtp(String userId, int otpCode, long expiryTime);
    boolean validateOtp(String userId, int otpCode);
}
