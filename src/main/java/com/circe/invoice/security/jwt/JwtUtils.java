package com.circe.invoice.security.jwt;

import com.circe.invoice.security.CurrentUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.ms}")
    private Integer jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Date now = new Date();
        return Jwts.builder()
                .setSubject(currentUser.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getLoginFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            LOGGER.error("Invalid JWT signature : {}", e.getMessage());
        }catch (MalformedJwtException e){
            LOGGER.error("Invalid JWT token : {}", e.getMessage());
        }catch (ExpiredJwtException e){
            LOGGER.error("Expired JWT token : {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            LOGGER.error("Unsupported JWT token : {}", e.getMessage());
        }catch (IllegalArgumentException e){
            LOGGER.error("Empty JWT token : {}", e.getMessage());
        }
        return false;
    }
}
