package com.circe.invoice.dto.invoice;

import com.circe.invoice.dto.client.ClientDto;
import com.circe.invoice.dto.designation.DesignationDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    private Long id;
    private String invoiceDate;
    private String expirityDate;
    private Float totalTaxes;
    private Float totalNoTaxes;
    private Float totalWithTaxes;
    private ClientDto client;
    private List<DesignationDto> designations = new ArrayList<>();


}
