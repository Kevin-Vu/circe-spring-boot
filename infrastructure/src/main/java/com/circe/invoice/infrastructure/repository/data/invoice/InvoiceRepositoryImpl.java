package com.circe.invoice.infrastructure.repository.data.invoice;

import com.circe.invoice.domain.dto.invoice.InvoiceDto;
import com.circe.invoice.domain.repository.data.InvoiceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InvoiceRepositoryImpl implements InvoiceRepository {

  private final JpaInvoiceRepository jpaInvoiceRepository;
  private final InvoiceMapper invoiceMapper;

  @Override
  public List<InvoiceDto> findAllByCustomerId(Integer id) {
    List<InvoiceEntity> invoices = this.jpaInvoiceRepository.findAllByCustomerId(id);
    return this.invoiceMapper.convertListInvoiceEntity(invoices);
  }
}
