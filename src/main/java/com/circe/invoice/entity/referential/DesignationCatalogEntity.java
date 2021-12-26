package com.circe.invoice.entity.referential;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "r_designation_catalog")
public class DesignationCatalogEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "designation_catalog_generator", sequenceName = "designation_catalog_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designation_catalog_generator")
    @Column(name = "dec_id")
    private Integer id;

    @Column(name = "dec_name", unique = true)
    private String name;

    @Column(name = "dec_price_no_taxes", scale = 2, nullable = false)
    private Float priceNoTaxes;

    @ManyToOne
    @JoinColumn(name = "dec_ptr_pdt_id")
    private ProductTypeEntity productType;

    @Column(name = "dec_created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "dec_created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "dec_last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "dec_last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
