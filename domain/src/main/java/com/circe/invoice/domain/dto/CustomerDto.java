package com.circe.invoice.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Builder
public class CustomerDto implements Serializable {

  private Integer id;
  private String firstname;
  private String lastname;
  private String telephone;
  private String address;
  private String postalCode;
  private String city;
  private CompanyEntity compagny;
  private String createdBy;
  private LocalDateTime createDate;
  private String lastModifiedBy;
  private LocalDateTime lastModifiedDate;