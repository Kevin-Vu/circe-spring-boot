package com.circe.invoice.domain.repository.data;

import com.circe.invoice.infrastructure.repository.data.company.CompanyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository  {
  CompanyEntity findByName(String name);
}
