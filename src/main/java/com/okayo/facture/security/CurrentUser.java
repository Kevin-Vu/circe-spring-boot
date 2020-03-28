package com.okayo.facture.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CurrentUser extends User {

    private Long id;
    private String companyName;

    public CurrentUser(String codeClient, String password, List<GrantedAuthority> authorities, Long id, String companyName) {

        super(codeClient, password, authorities);
        this.id = id;
        this.companyName = companyName;
    }
}
