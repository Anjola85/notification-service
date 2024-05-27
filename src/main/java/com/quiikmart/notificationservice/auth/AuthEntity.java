package com.quiikmart.notificationservice.auth;

import com.quiikmart.notificationservice.common.entity.CommonEntity;
import com.quiikmart.notificationservice.mobile.MobileEntity;
import com.quiikmart.notificationservice.user.UserEntity;
import jakarta.persistence.*;

@Entity(name = "auth")
public class AuthEntity extends CommonEntity {
    @Column(unique = true)
    private String email;

    @Column(name = "account_verified", nullable = false)
    private boolean accountVerified = false;

    @Column(name = "verification_code")
    private int otpCode;

    @Column(name = "verification_code_expiration")
    private long otpExpiry;

    @OneToOne(mappedBy = "auth", cascade = CascadeType.ALL, orphanRemoval = true)
    private MobileEntity mobile;

    @OneToOne(mappedBy = "auth", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserEntity user;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public int getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(int otpCode) {
        this.otpCode = otpCode;
    }

    public long getOtpExpiry() {
        return otpExpiry;
    }

    public void setOtpExpiry(long otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    public MobileEntity getMobile() {
        return mobile;
    }

    public void setMobile(MobileEntity mobile) {
        this.mobile = mobile;
    }
}
