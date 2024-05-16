package com.taleTrade.MailSender.controller;

import com.taleTrade.MailSender.component.AuthenticationComponent;
import com.taleTrade.MailSender.models.Response;
import com.taleTrade.MailSender.models.request.AccountActivationRequest;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationComponent authenticationComponent;

    @PostMapping("/sendVerificationEmail")
    public ResponseEntity<Response> activateAccount(
            @RequestBody AccountActivationRequest accountActivationRequest) throws MessagingException {
        System.out.println(accountActivationRequest);
        Response response = authenticationComponent.sendMail(accountActivationRequest);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

}
