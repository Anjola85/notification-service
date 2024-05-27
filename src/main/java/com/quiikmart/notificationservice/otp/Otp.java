package com.quiikmart.notificationservice.otp;

import java.time.Instant;

public class Otp {
    private int code;
    private long expiryTime;

    public Otp(int code, long expiryTime) {
        this.code = code;
        this.expiryTime = expiryTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public long getExpiryTimeInMinutes() {
        long currentTimeMillis = Instant.now().toEpochMilli();
        long durationMillis = expiryTime - currentTimeMillis;
        return durationMillis / (1000 * 60);
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

}
