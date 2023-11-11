package com.circe.invoice.repository.referential;

import com.circe.invoice.entity.referential.RightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightRepository extends JpaRepository<RightEntity, Integer> {}
