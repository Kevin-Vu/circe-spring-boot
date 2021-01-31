package com.circe.invoice.repository.referential;

import com.circe.invoice.entity.referential.DesignationCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationCatalogRepository extends JpaRepository<DesignationCatalogEntity, Integer> {

}
