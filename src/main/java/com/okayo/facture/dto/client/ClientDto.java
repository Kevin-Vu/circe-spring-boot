package com.okayo.facture.dto.client;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String clientCode;
    private String email;
    private String authority;
    private String company;

}
