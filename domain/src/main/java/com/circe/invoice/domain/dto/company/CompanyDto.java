package com.circe.invoice.domain.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

  private Integer id;
  private String name;
  private String address;
  private String city;
  private Integer postalCode;
  
}
