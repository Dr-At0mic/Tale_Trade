package com.taleTrade.Authentication.service;

import com.taleTrade.Authentication.entity.User;
import com.taleTrade.Authentication.exception.ApplicationException;
import com.taleTrade.Authentication.exception.ErrorCodes;
import com.taleTrade.Authentication.models.Response;
import com.taleTrade.Authentication.repository.UserAuthRepository;
import com.taleTrade.Authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private UserRepository userRepository;

    public Response userLogin(String emailAddress, String password) {
        //if the account is not activated then send a verification email
        //change if the user is acitvated the account then send the access token
        //change all the controller and remove multiple fetching which is uncessary
        //make the code more optimized and simple
        User user = userRepository.findByEmailAddress(emailAddress);
        if(null == user)
            throw new ApplicationException(
                    ErrorCodes.INVALID_CREDENTIALS,
                    "No User Found",
                    HttpStatus.NON_AUTHORITATIVE_INFORMATION
            );
        if(0 == user.getAccountStatus())
            throw new ApplicationException(
                    ErrorCodes.UNAUTHORIZED,
                    "Account Not Verified",
                    HttpStatus.UNAUTHORIZED
            );
        user.setLastLoginDate(LocalDateTime.now());
        user = userRepository.save(user);
        return Response.builder()
                .status(true)
                .message("Login Success")
                .httpStatus(HttpStatus.OK)
                .data(userAuthRepository.findByUserId(user).getRefreshToken())
                .build();
    }
}
