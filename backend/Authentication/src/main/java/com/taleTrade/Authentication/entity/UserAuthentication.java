package com.taleTrade.Authentication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAuthId;
    @Column
    private String refreshToken;
    @Column
    private LocalDateTime accountCreationDate;
    @Column
    @NotBlank(message = "password cannot be empty")
    private String password;
    @JoinColumn(unique = true,updatable = false)
    @OneToOne(orphanRemoval = true,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User userId;
}
