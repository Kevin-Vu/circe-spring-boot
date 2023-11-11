package com.circe.invoice.repository.data;

import com.circe.invoice.entity.data.InvoiceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {

  List<InvoiceEntity> findAllByCustomerId(Integer id);
}
