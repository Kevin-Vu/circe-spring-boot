package com.circe.invoice.security;

import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Accessors(chain = true)
public class CurrentUser extends User {

  private Integer id;
  private String email;
  private LocalDateTime expirationDatePwd;
  private LocalDateTime startDateAccess;
  private LocalDateTime endDateAccess;

  public CurrentUser(
      String userCode,
      String password,
      List<GrantedAuthority> authorities,
      Integer id,
      String email,
      LocalDateTime expirationDatePwd,
      LocalDateTime startDateAccess,
      LocalDateTime endDateAccess) {

    super(userCode, password, authorities);
    this.id = id;
    this.email = email;
    this.expirationDatePwd = expirationDatePwd;
    this.startDateAccess = startDateAccess;
    this.endDateAccess = endDateAccess;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.endDateAccess != null && this.endDateAccess.isAfter(LocalDateTime.now());
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.startDateAccess != null && this.startDateAccess.isBefore(LocalDateTime.now());
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.expirationDatePwd != null && this.expirationDatePwd.isAfter(LocalDateTime.now());
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
