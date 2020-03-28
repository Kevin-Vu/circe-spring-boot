package com.okayo.facture.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
@Data
@Accessors(chain = true)
public class InvoiceEntity {


    @Id
    @SequenceGenerator(name = "invoice_generator", sequenceName = "invoice_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_date", nullable = false)
    private Timestamp invoiceDate;

    @Column(name = "expirity_date", nullable = false)
    private Timestamp expirityDate;

    @Column(name = "total_taxes", scale = 2, nullable = false)
    private Float totalTaxes;

    @Column(name = "total_no_taxes", scale = 2, nullable = false)
    private Float totalNoTaxes;

    @Column(name = "total_with_taxes", scale = 2, updatable = false, nullable = false)
    private Float totalWithTaxes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_client")
    private ClientEntity client;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DesignationEntity> designations = new ArrayList<>();

}
