package com.circe.invoice.infrastructure.repository.data.designation;

import com.circe.invoice.domain.repository.data.DesignationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DesignationRepositoryImpl implements DesignationRepository {

  private JpaDesignationRepository jpaDesignationRepository;

}
