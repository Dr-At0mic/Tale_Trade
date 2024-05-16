package com.taleTrade.Authentication.component;

import com.taleTrade.Authentication.models.Response;
import com.taleTrade.Authentication.models.authenticationRequest.AuthenticationRequest;
import com.taleTrade.Authentication.service.LoginService;
import com.taleTrade.Authentication.service.RegisterService;
import com.taleTrade.Authentication.systemUtils.BaseEncodeDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationComponent {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private InvokeAnotherService invokeAnotherService;

    @Autowired
    private LoginService loginService;

    public Response newUser(AuthenticationRequest authenticationRequest) {
        Response response = registerService.createNewUser(authenticationRequest);
        if (response.isStatus()) {
            response.setMessage("Verification Email Send to you Account");
            System.out.println(response.getData().toString());
            invokeAnotherService.SendVerificationMail(authenticationRequest.getEmailAddress(), response.getData().toString());
        }
        return response;
    }


    public Response verifyUser(AuthenticationRequest authenticationRequest) {
        return loginService.userLogin(authenticationRequest.getEmailAddress(),authenticationRequest.getPassword());
    }

    public Response verifyEmail(String token) {
        return registerService.verifyEmail(BaseEncodeDecode.decode(token));
    }
}
