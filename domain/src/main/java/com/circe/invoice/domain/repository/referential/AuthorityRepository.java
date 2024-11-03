package com.circe.invoice.domain.repository.referential;

import com.circe.invoice.domain.dto.authority.AuthorityDto;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository {
  Optional<AuthorityDto> findByName(String name);
}
