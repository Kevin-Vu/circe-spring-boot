package com.circe.invoice.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Integer id;
  private String firstname;
  private String lastname;
  private String userCode;
  private String email;
  private String authority;
  private String langCode;
}
