package com.quiikmart.notificationservice.otp;

import com.quiikmart.notificationservice.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;

@Service
public class OtpServiceImpl implements OtpService {
    private static final int OTP_LENGTH = 6;
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes in milliseconds

    private final SecureRandom random = new SecureRandom();

    @Autowired
    private AuthService authService;

    /**
     * @param userId
     * @return otp
     */
    @Override
    public Otp generateOtp() {
        int code = random.nextInt(9000) + 1000;
        long expiryTime = Instant.now().toEpochMilli() + OTP_VALID_DURATION; // time in milliseconds
        return new Otp(code, expiryTime);
    }
}
