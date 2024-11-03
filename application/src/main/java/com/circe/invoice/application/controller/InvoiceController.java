package com.circe.invoice.application.controller;

import com.circe.invoice.domain.dto.invoice.InvoiceDto;
import com.circe.invoice.application.security.CurrentUser;
import com.circe.invoice.domain.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

  private final InvoiceService invoiceService;

  /**
   * Get all invoices for a given customer
   *
   * @param id : customer id
   * @return : list of InvoiceDto
   */
  @Operation(summary = "Get all the invoices for a given customer")
  @GetMapping(value = "/api/auth/invoice/all")
  public ResponseEntity<List<InvoiceDto>> getAllInvoicesForCustomer(
      CurrentUser user, @RequestParam Integer id) {
    return new ResponseEntity<>(invoiceService.getAllInvoicesForCustomer(id), HttpStatus.OK);
  }
}
