package com.okayo.facture.repository;

import com.okayo.facture.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    CompanyEntity findByName(String name);
}
