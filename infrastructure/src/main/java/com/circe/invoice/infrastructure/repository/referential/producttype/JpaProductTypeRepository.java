package com.circe.invoice.infrastructure.repository.referential.producttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductTypeRepository extends JpaRepository<ProductTypeEntity, Integer> {}
