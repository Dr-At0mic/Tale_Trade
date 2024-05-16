package com.taleTrade.Authentication.controller;

import com.taleTrade.Authentication.component.AuthenticationComponent;
import com.taleTrade.Authentication.exception.ApplicationException;
import com.taleTrade.Authentication.exception.ErrorCodes;
import com.taleTrade.Authentication.models.Response;
import com.taleTrade.Authentication.models.authenticationRequest.AuthenticationRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationComponent authenticationComponent;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
            Response response = authenticationComponent.newUser(authenticationRequest);
        if(response.isStatus())
            return new ResponseEntity<Response>(Response.builder()
                    .status(true)
                    .httpStatus(HttpStatus.OK)
                    .message(response.getMessage())
                    .build(),
                    HttpStatus.OK);
        throw new ApplicationException(
                ErrorCodes.INVALID_CREDENTIALS,
                response.getMessage(),
                HttpStatus.EXPECTATION_FAILED
        );
    }
    @GetMapping("/accountActivation")
    public ResponseEntity<Response> accountActivation(@Valid @RequestParam String token){
        Response response = authenticationComponent.verifyEmail(token);
        return new ResponseEntity<Response>(Response
                .builder()
                .message(response.getMessage())
                .status(true)
                .httpStatus(HttpStatus.OK)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<Response>(authenticationComponent.verifyUser(authenticationRequest), HttpStatus.OK);
    }

    //    @GetMapping("/test")
//    public String test(@RequestParam String token){
////        return UuidGenerator.TempName();
//        authenticationComponent.verifyEmail(token);
//        return null;
//    }


}
