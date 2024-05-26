package com.quiikmart.notificationservice.otp;

public interface OtpService {
    public int generateOtp(String userId);
    public boolean validateOtp(String userId, int otpCode);
}
