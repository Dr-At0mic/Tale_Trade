package com.taleTrade.MailSender.component;

import com.taleTrade.MailSender.models.Response;
import com.taleTrade.MailSender.models.request.AccountActivationRequest;
import com.taleTrade.MailSender.service.EmailSender;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationComponent {
    @Autowired
    private EmailSender mailSender;
    public Response sendMail(AccountActivationRequest accountActivationRequest) throws MessagingException {
        return mailSender.generateActivationMail(accountActivationRequest.getEmailAddress(),accountActivationRequest.getActivationToken());
    }
}
