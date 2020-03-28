package com.okayo.facture.dto.designation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateDesignationDto {

    private String name;
    private Float taxes;
    private Float unitPriceNoTaxes;
    private Integer quantity;
    private Float discount;
    private Float totalNoTaxes;

}
