package com.okayo.facture.repository;

import com.okayo.facture.entity.DesignationCatalogueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationCatalogueRepository extends JpaRepository<DesignationCatalogueEntity, Long> {

}
