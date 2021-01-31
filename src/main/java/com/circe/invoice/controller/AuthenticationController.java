package com.circe.invoice.controller;

import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.security.jwt.JwtLoginRequest;
import com.circe.invoice.security.jwt.JwtLoginResponse;
import com.circe.invoice.security.jwt.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = { "Authentication API" })
@SwaggerDefinition(tags = @Tag(name = "Authentication API"))
@RestController
@RequestMapping("/api/sign-in")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation(value = "Login")
    @PostMapping
    public ResponseEntity<JwtLoginResponse> authenticateUser(@Valid @RequestBody JwtLoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        List<String> rights = currentUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtLoginResponse response = new JwtLoginResponse(jwt, currentUser.getUsername(), rights);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
