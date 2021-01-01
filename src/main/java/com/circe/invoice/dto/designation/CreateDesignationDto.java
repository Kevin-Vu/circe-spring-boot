package com.circe.invoice.dto.designation;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDesignationDto {

    private String name;
    private Float taxes;
    private Float unitPriceNoTaxes;
    private Integer quantity;
    private Float discount;
    private Float totalNoTaxes;

}
