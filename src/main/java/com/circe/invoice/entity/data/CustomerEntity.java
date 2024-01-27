package com.circe.invoice.entity.data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.envers.NotAudited;
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
@Table(name = "d_customer")
public class CustomerEntity implements Serializable {

  @Id
  @SequenceGenerator(
      name = "customer_generator",
      sequenceName = "customer_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
  @Column(name = "cus_id")
  private Integer id;

  @Column(name = "cus_firstname")
  private String firstname;

  @Column(name = "cus_lastname")
  private String lastname;

  @Column(name = "cus_email")
  private String email;

  @Column(name = "cus_telephone")
  private String telephone;

  @Column(name = "cus_address")
  private String address;

  @Column(name = "cus_postal_code")
  private String postalCode;

  @Column(name = "cus_city")
  private String city;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cus_ptr_com_id")
  @NotAudited
  private CompanyEntity compagny;

  @Column(name = "cus_created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "cus_created_date")
  @CreatedDate
  private LocalDateTime createDate;

  @Column(name = "cus_last_modified_by")
  @LastModifiedBy
  private String lastModifiedBy;

  @Column(name = "cus_last_modified_date")
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
