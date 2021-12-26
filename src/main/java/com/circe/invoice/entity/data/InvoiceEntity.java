package com.circe.invoice.entity.data;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "d_invoice")
public class InvoiceEntity implements Serializable {


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

}
