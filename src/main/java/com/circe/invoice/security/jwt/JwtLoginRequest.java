package com.circe.invoice.security.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class JwtLoginRequest {

  @NotBlank private String login;
  @NotBlank private String password;
}
