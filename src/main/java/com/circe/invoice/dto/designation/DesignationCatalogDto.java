package com.circe.invoice.dto.designation;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DesignationCatalogDto {

    private String name;
    private Float priceNoTaxes;
    private String productType;
    private Float amount;

}
