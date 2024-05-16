package com.taleTrade.Authentication.service;

import com.taleTrade.Authentication.entity.User;
import com.taleTrade.Authentication.entity.UserAuthentication;
import com.taleTrade.Authentication.exception.ApplicationException;
import com.taleTrade.Authentication.exception.ErrorCodes;
import com.taleTrade.Authentication.models.Response;
import com.taleTrade.Authentication.models.authenticationRequest.AuthenticationRequest;
import com.taleTrade.Authentication.repository.UserAuthRepository;
import com.taleTrade.Authentication.repository.UserRepository;
import com.taleTrade.Authentication.systemUtils.BaseEncodeDecode;
import com.taleTrade.Authentication.systemUtils.JwtUtils;
import com.taleTrade.Authentication.systemUtils.SystemConstants;
import com.taleTrade.Authentication.systemUtils.UuidGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;

    public Response createNewUser(AuthenticationRequest authenticationRequest) {
        try {
            User user = userRepository.save(User.builder()
                            .emailAddress(authenticationRequest.getEmailAddress())
                            .accountStatus(0)
                            .fullName(ErrorCodes.UNAUTHORIZED)
                    .build());
            userAuthRepository.save(UserAuthentication.builder()
                            .userId(user)
                            .password(authenticationRequest.getPassword())
                            .accountCreationDate(LocalDateTime.now())
                    .build());
            return Response.builder()
                    .status(true)
                    .data(BaseEncodeDecode.encode(JwtUtils.generateToken(user, SystemConstants.ACTIVATION_EXPIRATION_TIME)))
                    .message("Account Creation Success")
                    .build();
        } catch (DataAccessException dae) {
            System.out.println("Error occurred while saving user to database: " + dae.getMessage());
            throw new ApplicationException(
                    ErrorCodes.EMAIL_ALREADY_REGISTERED,
                    "Email Address Duplication Detected",
                    HttpStatus.EXPECTATION_FAILED
            );
        } catch (Exception exception) {
            System.out.println("Unexpected error occurred: " + exception.getMessage());
            throw new ApplicationException(
                    ErrorCodes.INTERNAL_SERVER_ERROR,
                    "Unexpected error occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public Response verifyEmail(String token) {
        if(JwtUtils.isTokenExpired(token))
            throw new ApplicationException(
                    "token Expired",
                    ErrorCodes.EXPIRED_TOKEN,
                    HttpStatus.UNAUTHORIZED
            );
        Map<String, Object> map = JwtUtils.extractExtraClaims(token);
        System.out.println(map);
        Optional<User> optionalUser = userRepository.findById(((Number) map.get("User_Id")).longValue());
        if(optionalUser.isEmpty())
            throw new ApplicationException(
                    "User not found",
                    ErrorCodes.USER_NOT_FOUND,
                    HttpStatus.EXPECTATION_FAILED
            );
        if(optionalUser.get().getAccountStatus()==1){
            throw new ApplicationException(
                    "Already Verified",
                    ErrorCodes.EMAIL_ALREADY_REGISTERED,
                    HttpStatus.ALREADY_REPORTED
            );
        }
        optionalUser.get().setAccountStatus(1);
        optionalUser.get().setFullName("User-"+ UuidGenerator.TempName());
        userRepository.save(optionalUser.get());
        return Response.builder().message("User Verified").build();
    }
}
