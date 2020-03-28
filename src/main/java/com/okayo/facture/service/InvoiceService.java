package com.okayo.facture.service;

import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.invoice.InvoiceDto;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.entity.InvoiceEntity;

import java.util.List;

public interface InvoiceService {
    InvoiceEntity createFacture(List<CreateDesignationDto> createDesignationDtoList, ClientEntity clientEntity);
    List<InvoiceDto> getAllFactureForClient(ClientEntity clientEntity);
}
