package com.circe.invoice.domain.repository.referential;

import com.circe.invoice.domain.dto.user.UserDto;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

  Optional<UserDto> findByUserCode(String userCode);
}
