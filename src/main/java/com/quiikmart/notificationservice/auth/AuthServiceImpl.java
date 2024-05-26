package com.quiikmart.notificationservice.auth;

public class AuthServiceImpl implements AuthService {
    /**
     * @param userId
     * @param otpCode
     * @param expiryTime
     */
    @Override
    public void saveOtp(String userId, int otpCode, long expiryTime) {
        // TODO
//        OtpEntity otpEntity = new OtpEntity();
//        otpEntity.setUserId(userId);
//        otpEntity.setOtp(otp);
//        otpEntity.setExpiryTime(expiryTime);
//        otpRepository.save(otpEntity);
    }

    /**
     * @param userId
     * @param otpCode
     * @return
     */
    @Override
    public boolean validateOtp(String userId, int otpCode) {
        // TODO
//        Optional<OtpEntity> otpEntityOptional = otpRepository.findByUserIdAndOtp(userId, otp);
//        if (otpEntityOptional.isPresent()) {
//            OtpEntity otpEntity = otpEntityOptional.get();
//            if (Instant.now().toEpochMilli() < otpEntity.getExpiryTime()) {
//                otpRepository.deleteByUserId(userId);
//                return true;
//            } else {
//                otpRepository.deleteByUserId(userId);
//            }
//        }
        return false;
    }
}
