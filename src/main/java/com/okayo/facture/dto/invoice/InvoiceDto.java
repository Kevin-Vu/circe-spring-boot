package com.okayo.facture.dto.invoice;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.designation.DesignationDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
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
