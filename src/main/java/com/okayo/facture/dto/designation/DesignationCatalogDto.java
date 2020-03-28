package com.okayo.facture.dto.designation;

import com.okayo.facture.dto.taxe.TaxeDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DesignationCatalogDto {

    private String name;
    private Float priceNoTaxes;
    private TaxeDto taxeDto;

}
