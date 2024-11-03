package com.circe.invoice.infrastructure.repository.data.invoice;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {

  List<InvoiceEntity> findAllByCustomerId(Integer id);
}
