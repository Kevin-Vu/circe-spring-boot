package com.circe.invoice.service;

import com.circe.invoice.dto.invoice.InvoiceDto;

import java.util.List;

public interface InvoiceService {

    /**
     * Get the invoices a given customer id
     *
     * @param id : customer id
     *
     * @return : list of invoices
     */
    List<InvoiceDto> getAllInvoicesForCustomer(Integer id);
}
