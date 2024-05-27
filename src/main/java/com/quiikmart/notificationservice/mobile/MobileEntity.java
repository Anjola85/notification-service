package com.quiikmart.notificationservice.mobile;

import com.quiikmart.notificationservice.auth.AuthEntity;
import com.quiikmart.notificationservice.common.entity.CommonEntity;
import jakarta.persistence.*;

@Entity(name = "mobile")
public class MobileEntity extends CommonEntity {
    @Column(name = "phone_number", nullable = false, unique = true)
    private Long phoneNumber;

    @Column(name = "country_code", nullable = false)
    private Integer countryCode;

    @Column(name = "iso_type", nullable = false)
    private String isoType;

    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary = true;

    @OneToOne
    @JoinColumn(name = "auth_id")
    private AuthEntity auth;

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getIsoType() {
        return isoType;
    }

    public void setIsoType(String isoType) {
        this.isoType = isoType;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public AuthEntity getAuth() {
        return auth;
    }

    public void setAuth(AuthEntity auth) {
        this.auth = auth;
    }
}
