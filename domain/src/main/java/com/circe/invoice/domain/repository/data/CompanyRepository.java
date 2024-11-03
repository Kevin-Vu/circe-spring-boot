package com.circe.invoice.domain.repository.data;

import com.circe.invoice.domain.dto.company.CompanyDto;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository  {
  Optional<CompanyDto> findByName(String name);
}
