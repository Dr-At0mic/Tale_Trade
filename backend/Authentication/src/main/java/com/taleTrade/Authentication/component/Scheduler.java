package com.taleTrade.Authentication.component;

import com.taleTrade.Authentication.entity.User;
import com.taleTrade.Authentication.entity.UserAuthentication;
import com.taleTrade.Authentication.exception.ErrorCodes;
import com.taleTrade.Authentication.repository.UserAuthRepository;
import com.taleTrade.Authentication.repository.UserRepository;
import com.taleTrade.Authentication.systemUtils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void cleanupUnverifiedUsers() {
        List<User> listOfUsers = userRepository.findByFullName(ErrorCodes.UNAUTHORIZED);
        listOfUsers.forEach(user -> {
            if(user.getAccountStatus()==0) {
                UserAuthentication userAuthentication = userAuthRepository.findByUserId(user);
                if(userAuthentication.getAccountCreationDate().plusHours(24).isAfter(LocalDateTime.now())){
                    userAuthRepository.deleteById(userAuthentication.getUserAuthId());
                    userRepository.deleteById(user.getUserId());
                    System.out.println(user);
                    System.out.println(userAuthentication);
                }
            }
        });
    }
}
