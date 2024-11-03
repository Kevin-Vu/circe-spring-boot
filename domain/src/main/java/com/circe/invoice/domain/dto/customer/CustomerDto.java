package com.circe.invoice.domain.dto.customer;

import com.circe.invoice.domain.dto.company.CompanyDto;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Serializable {

  private Integer id;

  private String firstname;
  private String lastname;
  private String telephone;
  private String address;
  private String postalCode;
  private String city;
  private CompanyDto company;

}