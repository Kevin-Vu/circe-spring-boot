package com.circe.invoice.entity.referential;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "r_product_type")
public class ProductTypeEntity implements Serializable, Auditable<String, Integer, LocalDateTime> {

    @Id
    @SequenceGenerator(name = "product_type_generator", sequenceName = "product_type_sequence", allocationSize = 1)
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
