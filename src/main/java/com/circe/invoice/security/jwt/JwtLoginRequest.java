package com.circe.invoice.security.jwt;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class JwtLoginRequest {

    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
