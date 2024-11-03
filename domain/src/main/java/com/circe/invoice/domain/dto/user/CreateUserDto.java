package com.circe.invoice.domain.dto.user;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

  private String firstname;
  private String lastname;
  private String userCode;
  private String email;
  private String password;
  private String authority;
  private String langCode;
}
