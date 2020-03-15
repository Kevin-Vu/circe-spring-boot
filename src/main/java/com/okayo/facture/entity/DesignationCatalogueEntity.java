package com.okayo.facture.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "designation_catalogue")
@Data
@Accessors(chain = true)
public class DesignationCatalogueEntity {

    @Id
    @SequenceGenerator(name = "designation_catalogue_generator", sequenceName = "designation_catalogue_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designation_catalogue_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "tva", precision = 2, scale = 3, nullable = false)
    private float tva;

}
