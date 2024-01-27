package com.circe.invoice.security.jwt;

import com.circe.invoice.service.UserService;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class AuthTokenFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);

  private final JwtUtils jwtUtils;
  private final UserService userService;

  @Autowired
  public AuthTokenFilter(JwtUtils jwtUtils, UserService userService) {
    this.jwtUtils = jwtUtils;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(
      @Nullable HttpServletRequest request,
      @Nullable HttpServletResponse response,
      @Nullable FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = parseJwt(request);
      if (jwt != null && this.jwtUtils.validateJwtToken(jwt)) {
        String login = this.jwtUtils.getLoginFromJwtToken(jwt);
        UserDetails userDetails = userService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    } catch (Exception e) {
      LOGGER.error("Cannot create authorization for user : {}", e.getMessage());
    }
    if(request != null && response != null && filterChain != null)
      filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    if (request == null)
      return null;
    String headerAuth = request.getHeader("Authorization");
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer "))
      return headerAuth.substring(7);
    return null;
  }
}
