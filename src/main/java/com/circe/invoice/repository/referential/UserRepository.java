package com.circe.invoice.repository.referential;

import com.circe.invoice.entity.referential.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUserCode(String userCode);
}
