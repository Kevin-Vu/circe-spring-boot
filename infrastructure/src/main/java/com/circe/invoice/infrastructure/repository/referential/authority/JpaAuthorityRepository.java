package com.circe.invoice.infrastructure.repository.referential.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
  AuthorityEntity findByName(String name);
}
