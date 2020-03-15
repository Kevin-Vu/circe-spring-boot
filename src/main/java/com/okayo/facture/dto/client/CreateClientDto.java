package com.okayo.facture.dto.client;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateClientDto {

    private String name;
    private String adresse;
    private Integer codePostal;

}
