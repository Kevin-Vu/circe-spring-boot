package com.circe.invoice.domain.repository.data;

import com.circe.invoice.infrastructure.repository.data.invoice.InvoiceEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository {

  List<InvoiceEntity> findAllByCustomerId(Integer id);
}
