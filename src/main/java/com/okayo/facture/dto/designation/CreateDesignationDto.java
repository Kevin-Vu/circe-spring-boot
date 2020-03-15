package com.okayo.facture.dto.designation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateDesignationDto {

    private String name;
    private float tva;
    private float prixUnitHt;
    private Integer quantite;
    private float reduction;
    private float totalHt;

}
