package com.circe.invoice.controller;

import com.circe.invoice.dto.invoice.InvoiceDto;
import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.service.InvoiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    /**
     * Get all invoices for a given customer
     *
     * @param id : customer id
     *
     * @return : list of InvoiceDto
     */
    @ApiOperation(value = "Get all the invoices for a given customer")
    @GetMapping(value = "/api/auth/invoice/all")
    public ResponseEntity<List<InvoiceDto>> getAllInvoicesForCustomer(CurrentUser user, @RequestParam Integer id) {
        return new ResponseEntity<>(invoiceService.getAllInvoicesForCustomer(id), HttpStatus.OK);
    }


}
