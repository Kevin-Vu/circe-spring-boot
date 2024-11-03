package com.circe.invoice.infrastructure.repository.data.invoice;

import com.circe.invoice.domain.repository.data.InvoiceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InvoiceRepositoryImpl implements InvoiceRepository {

  private JpaInvoiceRepository jpaInvoiceRepository;

  @Override
  public List<InvoiceEntity> findAllByCustomerId(Integer id) {
    return List.of();
  }
}
