package com.circe.invoice.infrastructure.repository.data.company;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "d_company")
public class CompanyEntity implements Serializable {

  @Id
  @SequenceGenerator(
      name = "company_generator",
      sequenceName = "company_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_generator")
  @Column(name = "com_id")
  private Integer id;

  @Column(name = "com_name", unique = true, nullable = false)
  private String name;

  @Column(name = "com_address", nullable = false)
  private String address;

  @Column(name = "com_city", nullable = false)
  private String city;

  @Column(name = "com_postal_code", nullable = false)
  private Integer postalCode;

  @Column(name = "com_created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "com_created_date")
  @CreatedDate
  private LocalDateTime createDate;

  @Column(name = "com_last_modified_by")
  @LastModifiedBy
  private String lastModifiedBy;

  @Column(name = "com_last_modified_date")
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
