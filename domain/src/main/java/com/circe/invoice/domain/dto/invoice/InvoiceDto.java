package com.circe.invoice.domain.dto.invoice;

import com.circe.invoice.domain.dto.designation.DesignationDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

  private Integer id;
  private Timestamp invoiceDate;
  private Timestamp expirationDate;
  private Float totalTaxes;
  private Float totalNoTaxes;
  private Float totalWithTaxes;
  @Builder.Default private List<DesignationDto> designations = new ArrayList<>();
}
