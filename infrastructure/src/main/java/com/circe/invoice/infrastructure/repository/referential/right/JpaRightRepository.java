package com.circe.invoice.infrastructure.repository.referential.right;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRightRepository extends JpaRepository<RightEntity, Integer> {}
