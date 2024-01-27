package com.circe.invoice.entity.data;

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
@Table(name = "d_designation")
public class DesignationEntity implements Serializable {

  @Id
  @SequenceGenerator(
      name = "designation_generator",
      sequenceName = "designation_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designation_generator")
  @Column(name = "dsg_id")
  private Integer id;

  @Column(name = "dsg_name", updatable = false, nullable = false)
  private String name;

  @Column(name = "dsg_taxes", updatable = false, scale = 2, nullable = false)
  private Float taxes;

  @Column(name = "dsg_unit_price_no_taxes", updatable = false, scale = 2, nullable = false)
  private Float unitPriceNoTaxes;

  @Column(name = "dsg_quantity", nullable = false)
  private Integer quantity;

  @Column(name = "dsg_discount", scale = 2, nullable = false)
  private Float discount;

  @Column(name = "dsg_total_no_taxes", scale = 2, nullable = false)
  private Float totalNoTaxes;

  @Column(name = "dsg_total_taxes", scale = 2, nullable = false)
  private Float totalTaxes;

  @Column(name = "dsg_product_type")
  private String productType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dsg_ptr_ivc_id")
  private InvoiceEntity invoice;

  @Column(name = "dsg_created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "dsg_created_date")
  @CreatedDate
  private LocalDateTime createDate;

  @Column(name = "dsg_last_modified_by")
  @LastModifiedBy
  private String lastModifiedBy;

  @Column(name = "dsg_last_modified_date")
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
