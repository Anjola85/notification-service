package com.quiikmart.notificationservice.integration.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendGridService implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(SendGridService.class);

    private final String apiKey;
    private final SendGrid sendGrid;
    private final String senderEmail;

    public SendGridService(@Value("${sendgrid.api.key}") String apiKey,  @Value("${sendgrid.sender.email}") String senderEmail) {
        this.apiKey = apiKey;
        this.senderEmail = senderEmail;
        this.sendGrid = new SendGrid(apiKey);
    }

    @Override
    public void sendEmail(String to, String subject, String body) throws Exception {
        Email from = new Email(senderEmail);
        Email recipient = new Email(to);
        //TODO: this should be generated depending on the type of email
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, recipient, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            logger.info("Email sent to {} with status code: {}", to, response.getStatusCode());
        } catch (Exception e) {
            logger.error("Error sending email with message: {}", e.getMessage());
            throw e;
        }
    }
}
