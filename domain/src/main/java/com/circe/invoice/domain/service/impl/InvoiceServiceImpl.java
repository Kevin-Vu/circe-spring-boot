package com.circe.invoice.domain.service.impl;

import com.circe.invoice.domain.dto.invoice.InvoiceDto;
import com.circe.invoice.domain.mapper.InvoiceMapper;
import com.circe.invoice.domain.repository.data.InvoiceRepository;
import com.circe.invoice.domain.service.InvoiceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
