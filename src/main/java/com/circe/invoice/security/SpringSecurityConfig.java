package com.circe.invoice.security;

import com.circe.invoice.security.jwt.AuthEntryPointJwt;
import com.circe.invoice.security.jwt.AuthTokenFilter;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
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
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
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
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) ->
        web.expressionHandler(
            new DefaultWebSecurityExpressionHandler() {
              @Override
              protected SecurityExpressionOperations createSecurityExpressionRoot(
                  Authentication authentication, FilterInvocation fi) {
                WebSecurityExpressionRoot root =
                    (WebSecurityExpressionRoot)
                        super.createSecurityExpressionRoot(authentication, fi);
                root.setDefaultRolePrefix("RIGHT_");
                return root;
              }
            });
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

  // JSESSION ID EXAMPLE
  //    @Override
  //    protected void configure(HttpSecurity httpSecurity) throws Exception {
  //
  //        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
  //        httpSecurity.sessionManagement().sessionFixation().migrateSession();
  //
  //        httpSecurity
  //                .csrf()
  //                    .disable()
  //                .cors()
  //                .and()
  //                    .formLogin()
  //                    .loginProcessingUrl("/api/login")
  //                    .successHandler(new AuthentificationLoginSuccessHandler())
  //                    .failureHandler(new AuthenticationLoginFailureHandler())
  //                .and()
  //                    .logout()
  //                    .logoutUrl("/api/logout")
  //                    .logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
  //                    .invalidateHttpSession(true)
  //                .and()
  //                    .authorizeRequests()
  //                    .antMatchers("/api/auth/admin/**")
  //                    .hasAuthority(AuthorityEnum.ADMINISTRATOR.getValue())
  //                .and()
  //                    .authorizeRequests()
  //                    .antMatchers("/api/auth/manager/**")
  //                    .hasAnyAuthority(AuthorityEnum.MANAGER.getValue(),
  // AuthorityEnum.ADMINISTRATOR.getValue())
  //                .and()
  //                    .authorizeRequests()
  //                    .antMatchers("/api/auth/**")
  //                    .authenticated()
  //                .and()
  //                    .authorizeRequests()
  //                    .anyRequest()
  //                    .permitAll();
  //    }

  //    private static class AuthentificationLoginSuccessHandler extends
  // SimpleUrlAuthenticationSuccessHandler {
  //        @Override
  //        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse
  // response, Authentication authentication) {
  //            response.setStatus(HttpServletResponse.SC_OK);
  //        }
  //    }
  //
  //    private static class AuthenticationLoginFailureHandler extends
  // SimpleUrlAuthenticationFailureHandler{
  //        @Override
  //        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse
  // response, AuthenticationException exception) {
  //            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
  //        }
  //    }
  //
  //    private static class AuthentificationLogoutSuccessHandler extends
  // SimpleUrlLogoutSuccessHandler {
  //        @Override
  //        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
  // Authentication authentication)  {
  //            response.setStatus(HttpServletResponse.SC_OK);
  //        }
  //    }

}
