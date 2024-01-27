package com.circe.invoice.service.impl;

import com.circe.invoice.dto.invoice.InvoiceDto;
import com.circe.invoice.dto.mapper.InvoiceMapper;
import com.circe.invoice.repository.data.InvoiceRepository;
import com.circe.invoice.service.InvoiceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceMapper invoiceMapper;
  private final InvoiceRepository invoiceRepository;

  @Override
  public List<InvoiceDto> getAllInvoicesForCustomer(Integer id) {
    return invoiceMapper.convertListInvoiceEntity(invoiceRepository.findAllByCustomerId(id));
  }
}
