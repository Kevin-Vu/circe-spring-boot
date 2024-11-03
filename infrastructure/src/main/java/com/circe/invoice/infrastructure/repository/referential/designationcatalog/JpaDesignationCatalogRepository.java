package com.circe.invoice.infrastructure.repository.referential.designationcatalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDesignationCatalogRepository
    extends JpaRepository<DesignationCatalogEntity, Integer> {}
