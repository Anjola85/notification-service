package com.quiikmart.notificationservice.auth;

import com.quiikmart.notificationservice.otp.Otp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthRepository authRepository;

    /**
     * @param userId
     * @param otp
     */
    @Override
    public void saveOtp(String userId, Otp otp) {
        AuthEntity authEntity = authRepository.findByUserId(userId);
        if(authEntity != null) {
            authEntity.setOtpCode(otp.getCode());
            authEntity.setOtpExpiry(otp.getExpiryTime());
            authRepository.save(authEntity);
        } else {
            throw new RuntimeException("Auth entity not found for user id: " + userId);
        }
    }
}
