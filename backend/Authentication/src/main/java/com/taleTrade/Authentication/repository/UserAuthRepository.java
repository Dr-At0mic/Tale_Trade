package com.taleTrade.Authentication.repository;

import com.taleTrade.Authentication.entity.User;
import com.taleTrade.Authentication.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthentication,Long> {

    UserAuthentication findByUserId(User user);
}
