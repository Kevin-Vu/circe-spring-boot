package com.circe.invoice.security.jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtLoginResponse {

    private String token;
    private String type = "Bearer";
    private String login;
    private List<String> rights;

    public JwtLoginResponse(String token, String login, List<String> rights){
        this.token = token;
        this.login = login;
        this.rights = rights;
    }
}
