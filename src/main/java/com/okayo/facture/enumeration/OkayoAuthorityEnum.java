package com.okayo.facture.enumeration;

import lombok.Getter;

public enum OkayoAuthorityEnum {

    ADMINISTRATOR("ADMINISTRATOR"),
    MANAGER("MANAGER"),
    USER("USER");

    @Getter
    private final String value;

    OkayoAuthorityEnum(String value) {
        this.value = value;
    }
}