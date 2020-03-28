package com.okayo.facture.repository;

import com.okayo.facture.entity.DesignationCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationCatalogRepository extends JpaRepository<DesignationCatalogEntity, Long> {

}
