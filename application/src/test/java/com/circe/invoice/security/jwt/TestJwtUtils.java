package com.circe.invoice.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class TestJwtUtils {

  @Test
  void toto() {
    String s =
        Jwts.builder()
            .subject("admin")
            .issuedAt(new Date())
            .expiration(new Date(new Date().getTime() + 604800000))
            .signWith(
                Keys.hmacShaKeyFor(
                    "spring_session_is_way_simpler_to_implement".getBytes(StandardCharsets.UTF_8)))
            .compact();
    System.out.printf(s);
    System.out.println(new BCryptPasswordEncoder().encode("admin"));
    ;
  }
}
