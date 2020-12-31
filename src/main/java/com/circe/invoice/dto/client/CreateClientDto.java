package com.circe.invoice.dto.client;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateClientDto {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String authority;
    private String company;

}
