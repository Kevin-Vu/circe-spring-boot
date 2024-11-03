package com.circe.invoice.infrastructure.repository.data.company;


import com.circe.invoice.domain.repository.data.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyRepositoryImpl implements CompanyRepository {

  private JpaCompanyRepository jpaCompanyRepository;


  @Override
  public CompanyEntity findByName(String name) {

    jpaCompanyRepository.findByName(name);

  }
}
