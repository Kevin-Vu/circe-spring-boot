package com.okayo.facture.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "designation")
@Data
@Accessors(chain = true)
public class DesignationEntity {

    @Id
    @SequenceGenerator(name = "designation_generator", sequenceName = "designation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designation_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @Column(name = "taxes", updatable = false, scale = 2, nullable = false)
    private Float taxes;

    @Column(name = "unit_price_no_taxes", updatable = false, scale = 2, nullable = false)
    private Float unitPriceNoTaxes;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "discount", scale = 2, nullable = false)
    private Float discount;

    @Column(name = "total_no_taxes", scale = 2, nullable = false)
    private Float totalNoTaxes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_invoice")
    private InvoiceEntity invoice;


}
