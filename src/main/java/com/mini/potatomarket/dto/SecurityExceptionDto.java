package com.mini.potatomarket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SecurityExceptionDto {

    private int statusCode;
    private String message;

    public SecurityExceptionDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}