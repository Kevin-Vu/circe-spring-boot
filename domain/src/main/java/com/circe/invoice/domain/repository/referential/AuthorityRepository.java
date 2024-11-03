package com.circe.invoice.domain.repository.referential;

import com.circe.invoice.infrastructure.repository.referential.authority.AuthorityEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository {
  AuthorityEntity findByName(String name);
}
