package com.quiikmart.notificationservice.integration.sms;
import com.quiikmart.notificationservice.dto.MobileNumber;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import static com.quiikmart.notificationservice.common.util.validator.mobile.isMobileNumberValid;

@Service
public class TwilioSmsService implements SmsService{
    private static final Logger logger = LoggerFactory.getLogger(TwilioSmsService.class);

    private final String accountSid;
    private final String authToken;
    private final String fromNumber;

    public TwilioSmsService(@Value("${twilio.account.sid}") String accountSid,
                            @Value("${twilio.auth.token}") String authToken,
                            @Value("${twilio.from.number}") String fromNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromNumber = fromNumber;
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendSms(String phoneNumber, String message) {
        try {
            if(isMobileNumberValid(phoneNumber)) {
                PhoneNumber to = new PhoneNumber(phoneNumber);
                PhoneNumber from = new PhoneNumber(fromNumber);
                Message twilioMessage = Message.creator(to, from, message).create();
                logger.info("Sent SMS to {}: {}", phoneNumber, twilioMessage.getSid());
                // TODO: return the value
            } else {
                logger.error("Invalid phone number provided: {}", phoneNumber);
                throw new IllegalArgumentException("Invalid phone number: " + phoneNumber);
            }
        } catch (ApiException e) {
            logger.error("Failed to send SMS: {}", e.getMessage());
            throw new RuntimeException("Failed to send SMS due to API error: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred when sending SMS: {}", e.getMessage());
            throw new RuntimeException("Unexpected error occurred when sending SMS", e);
        }
    }
}
