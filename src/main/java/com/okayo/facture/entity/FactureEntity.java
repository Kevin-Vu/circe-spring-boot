package com.okayo.facture.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "facture")
@Data
@Accessors(chain = true)
public class FactureEntity {

    @Id
    @SequenceGenerator(name = "facture_generator", sequenceName = "facture_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facture_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "date_facturation", nullable = false)
    private Timestamp dateFacturation;

    @Column(name = "date_echeance", nullable = false)
    private Timestamp dateEcheance;

    @Column(name = "total_tva", scale = 2, nullable = false)
    private float totalTva;

    @Column(name = "total_ht", scale = 2, nullable = false)
    private float totalHt;

    @Column(name = "total_ttc", scale = 2, updatable = false, nullable = false)
    private float totalTtc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_client")
    private ClientEntity client;

    @OneToMany(mappedBy = "facture", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DesignationEntity> designations = new ArrayList<>();

}
