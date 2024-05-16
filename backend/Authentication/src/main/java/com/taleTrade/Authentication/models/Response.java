package com.taleTrade.Authentication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private boolean status;
    private String message;
    private Object data;
    private HttpStatus httpStatus;
}
