package com.okayo.facture.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "designation_catalog")
@Data
@Accessors(chain = true)
public class DesignationCatalogEntity {

    @Id
    @SequenceGenerator(name = "designation_catalog_generator", sequenceName = "designation_catalog_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designation_catalog_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price_no_taxes", scale = 2, nullable = false)
    private Float priceNoTaxes;

    @ManyToOne
    @JoinColumn(name = "fk_id_taxe")
    private TaxeEntity taxeEntity;

}
