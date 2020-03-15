package com.okayo.facture.dto.client;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientDto {

    private Long id;
    private String name;
    private String codeClient;
    private String adresse;
    private Integer codePostal;

}
