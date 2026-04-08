package com.circe.invoice.security;

import com.circe.invoice.security.jwt.AuthEntryPointJwt;
import com.circe.invoice.security.jwt.AuthTokenFilter;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

  private final AuthEntryPointJwt authEntryPointJwt;
  private final AuthTokenFilter authTokenFilter;

  @Resource(name = "userDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  public SpringSecurityConfig(AuthEntryPointJwt authEntryPointJwt, AuthTokenFilter authTokenFilter) {
    this.authEntryPointJwt = authEntryPointJwt;
    this.authTokenFilter = authTokenFilter;
  }

  @Bean(name = "passwordEncoder")
  public static PasswordEncoder passwordencoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordencoder());
    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordencoder());
    return authenticationManagerBuilder.build();
  }

  @Bean
  public GrantedAuthorityDefaults grantedAuthorityDefaults() {
    return new GrantedAuthorityDefaults("RIGHT_");
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
    corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
    corsConfiguration.setAllowedMethods(
        Arrays.asList(
            RequestMethod.POST.name(),
            RequestMethod.PATCH.name(),
            RequestMethod.PUT.name(),
            RequestMethod.DELETE.name(),
            RequestMethod.GET.name(),
            RequestMethod.OPTIONS.name(),
            RequestMethod.TRACE.name(),
            RequestMethod.HEAD.name()));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }

  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling(e -> e.authenticationEntryPoint(authEntryPointJwt))
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").authenticated())
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
  }
}
