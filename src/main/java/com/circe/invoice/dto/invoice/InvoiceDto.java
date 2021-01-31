package com.circe.invoice.dto.invoice;

import com.circe.invoice.dto.designation.DesignationDto;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private List<DesignationDto> designations = new ArrayList<>();


}
