package com.circe.invoice.entity.referentiel;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "r_designation_catalog")
public class DesignationCatalogEntity implements Auditable<String, Integer, LocalDateTime> {

    @Id
    @SequenceGenerator(name = "designation_catalog_generator", sequenceName = "designation_catalog_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designation_catalog_generator")
    @Column(name = "dec_id")
    private Long id;

    @Column(name = "dec_name", unique = true)
    private String name;

    @Column(name = "dec_price_no_taxes", scale = 2, nullable = false)
    private Float priceNoTaxes;

    @ManyToOne
    @JoinColumn(name = "dec_ptr_pro_id")
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

    @Override
    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(this.createdBy);
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return Optional.ofNullable(this.createDate);
    }

    @Override
    public void setCreatedDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return Optional.ofNullable(this.lastModifiedDate);
    }

    @Override
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
