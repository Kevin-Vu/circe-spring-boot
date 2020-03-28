package com.okayo.facture.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BCryptManagerUtil {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BCryptManagerUtil(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}