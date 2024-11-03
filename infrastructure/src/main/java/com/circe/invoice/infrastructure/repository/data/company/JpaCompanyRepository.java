package com.circe.invoice.infrastructure.repository.data.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCompanyRepository extends JpaRepository<CompanyEntity, Integer> {

  CompanyEntity findByName(String name);
}
