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

    @Column(name = "tva", updatable = false, precision = 2, scale = 3, nullable = false)
    private float tva;

    @Column(name = "prix_unit_ht", updatable = false, precision = 2, scale = 10, nullable = false)
    private float prixUnitHt;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @Column(name = "reduction", precision = 2, scale = 3, nullable = false) // Percentage 0 to 100
    private float reduction;

    @Column(name = "total_ht", precision = 2, scale = 10, nullable = false)
    private float totalHt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_facture")
    private FactureEntity facture;


}
