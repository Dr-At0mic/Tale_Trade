package com.taleTrade.Authentication.repository;

import com.taleTrade.Authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByFullName(String FullName);

    User findByEmailAddress(String emailAddress);
}
