package com.circe.invoice.domain.repository.referential;

import com.circe.invoice.infrastructure.repository.referential.user.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

  UserEntity findByUserCode(String userCode);
}
