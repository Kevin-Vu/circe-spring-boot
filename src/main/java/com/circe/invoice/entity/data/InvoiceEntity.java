package com.circe.invoice.entity.data;

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
@Table(name = "d_invoice")
public class InvoiceEntity implements Serializable, Auditable<String, Integer, LocalDateTime> {


    @Id
    @SequenceGenerator(name = "invoice_generator", sequenceName = "invoice_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_generator")
    @Column(name = "ivc_id")
    private Integer id;

    @Column(name = "ivc_invoice_date", nullable = false)
    private Timestamp invoiceDate;

    @Column(name = "ivc_expiration_date", nullable = false)
    private Timestamp expirationDate;

    @Column(name = "ivc_total_taxes", scale = 2, nullable = false)
    private Float totalTaxes;

    @Column(name = "ivc_total_no_taxes", scale = 2, nullable = false)
    private Float totalNoTaxes;

    @Column(name = "ivc_total_with_taxes", scale = 2, updatable = false, nullable = false)
    private Float totalWithTaxes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ivc_ptr_cst_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DesignationEntity> designations = new ArrayList<>();

    @Column(name = "ivc_created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "ivc_created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "ivc_last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "ivc_last_modified_date")
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
