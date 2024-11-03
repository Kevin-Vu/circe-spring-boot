package com.circe.invoice.infrastructure.repository.referential.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {

  UserEntity findByUserCode(String userCode);
}
