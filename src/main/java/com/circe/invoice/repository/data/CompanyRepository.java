package com.circe.invoice.repository.data;

import com.circe.invoice.entity.data.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

  CompanyEntity findByName(String name);
}
