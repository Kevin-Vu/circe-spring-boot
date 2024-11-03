package com.circe.invoice.infrastructure.repository.data.customer;

import com.circe.invoice.domain.repository.data.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerRepositoryImpl implements CustomerRepository {

  private JpaCustomerRepository jpaCustomerRepository;

}
