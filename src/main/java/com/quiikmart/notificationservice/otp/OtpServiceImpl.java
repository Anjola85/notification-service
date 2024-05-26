package com.quiikmart.notificationservice.otp;

import com.quiikmart.notificationservice.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;

@Service
public class OtpServiceImpl implements OtpService {
    private static final int OTP_LENGTH = 6;
    private static final long OTP_VALID_DURATION = 0;

    private final SecureRandom random = new SecureRandom();

    @Autowired
    private AuthService authService;

    /**
     * @param userId
     * @return
     */
    @Override
    public int generateOtp(String userId) {
        int otp = random.nextInt(9000) + 1000;
        long expiryTime = Instant.now().toEpochMilli() + OTP_VALID_DURATION;
        authService.saveOtp(userId, otp, expiryTime);
        return otp;
    }

    /**
     * @param userId
     * @param otpCode
     * @return
     */
    @Override
    public boolean validateOtp(String userId, int otpCode) {
        return authService.validateOtp(userId, otpCode);
    }
}
