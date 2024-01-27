package com.circe.invoice.entity.referential;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "r_product_type")
public class ProductTypeEntity implements Serializable {

  @Id
  @SequenceGenerator(
      name = "product_type_generator",
      sequenceName = "product_type_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_generator")
  @Column(name = "pdt_id")
  private Integer id;

  @Column(name = "pdt_product_type")
  private String productType;

  @Column(name = "pdt_amount", scale = 2, nullable = false)
  private Float amount;

  @Column(name = "pdt_expiration_date", nullable = false)
  private Timestamp expirationDate;

  @OneToMany(mappedBy = "productType", fetch = FetchType.LAZY)
  @Builder.Default
  private List<DesignationCatalogEntity> designationCatalogList = new ArrayList<>();

  @Column(name = "pdt_created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "pdt_created_date")
  @CreatedDate
  private LocalDateTime createDate;

  @Column(name = "pdt_last_modified_by")
  @LastModifiedBy
  private String lastModifiedBy;

  @Column(name = "pdt_last_modified_date")
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
