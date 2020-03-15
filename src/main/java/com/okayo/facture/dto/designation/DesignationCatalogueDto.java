package com.okayo.facture.dto.designation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DesignationCatalogueDto {

    private String name;
    private float tva;

}
