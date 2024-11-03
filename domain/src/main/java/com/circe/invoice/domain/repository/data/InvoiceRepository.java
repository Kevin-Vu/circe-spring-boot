package com.circe.invoice.domain.repository.data;

import com.circe.invoice.domain.dto.invoice.InvoiceDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository {

  List<InvoiceDto> findAllByCustomerId(Integer id);
}
