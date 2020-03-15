package com.okayo.facture.repository;

import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.entity.FactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<FactureEntity, Long> {

    List<FactureEntity> findAllByClient(ClientEntity clientEntity);

}
