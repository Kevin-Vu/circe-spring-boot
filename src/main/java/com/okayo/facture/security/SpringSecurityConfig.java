package com.okayo.facture.security;

import com.okayo.facture.enumeration.OkayoAuthorityEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Bean(name = "passwordEncoder")
    public static PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordencoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));

        corsConfiguration.setAllowedOrigins(Arrays.asList("*")); // TODO set in properties
        corsConfiguration.setAllowedMethods(Arrays.asList(
                RequestMethod.POST.name(),
                RequestMethod.PATCH.name(),
                RequestMethod.PUT.name(),
                RequestMethod.DELETE.name(),
                RequestMethod.GET.name(),
                RequestMethod.OPTIONS.name(),
                RequestMethod.TRACE.name(),
                RequestMethod.HEAD.name()
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        httpSecurity.sessionManagement().sessionFixation().none();

        httpSecurity
                .csrf()
                    .disable()
                .cors()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/api/login")
                    .successHandler(new AuthentificationLoginSuccessHandler())
                    .failureHandler(new AuthenticationLoginFailureHandler())
                .and()
                    .logout()
                    .logoutUrl("/api/logout")
                    .logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
                    .invalidateHttpSession(true)
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/admin/**")
                    .hasAuthority(OkayoAuthorityEnum.ADMINISTRATOR.getValue())
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/manager/**")
                    .hasAnyAuthority(OkayoAuthorityEnum.MANAGER.getValue(), OkayoAuthorityEnum.ADMINISTRATOR.getValue())
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/**")
                    .authenticated()
                .and()
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();
    }


    private static class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private static class AuthenticationLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private static class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
