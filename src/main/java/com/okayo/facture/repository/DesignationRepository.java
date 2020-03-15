package com.okayo.facture.repository;

import com.okayo.facture.entity.DesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<DesignationEntity, Long> {
}
