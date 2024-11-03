package com.circe.invoice.domain.dto.designation;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DesignationDto {

  private Long id;
  private String name;
  private Float taxes;
  private Float unitPriceNoTaxes;
  private Integer quantity;
  private Float discount;
  private Float totalNoTaxes;
  private String productType;
}
