package com.taleTrade.Authentication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank(message = "Full Name Cannot be Empty")
    @Column
    private String fullName;
    @Email(message = "Email Address is not Valid")
    @NotBlank(message = "Email Address Cannot be Empty")
    @Column(unique = true)
    private String emailAddress;
    @Column
    private LocalDateTime lastLoginDate;
    @Column
    private String profileUrl;
    @Column
    private int accountStatus;
}
