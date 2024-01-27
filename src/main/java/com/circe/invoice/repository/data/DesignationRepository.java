package com.circe.invoice.repository.data;

import com.circe.invoice.entity.data.DesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<DesignationEntity, Integer> {}
