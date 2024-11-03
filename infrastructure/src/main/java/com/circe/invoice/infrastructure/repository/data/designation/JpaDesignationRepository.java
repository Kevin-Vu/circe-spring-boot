package com.circe.invoice.infrastructure.repository.data.designation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDesignationRepository extends JpaRepository<DesignationEntity, Integer> {}
