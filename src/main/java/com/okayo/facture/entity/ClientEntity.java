package com.okayo.facture.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data
@Accessors(chain = true)
public class ClientEntity {

    @Id
    @SequenceGenerator(name = "client_generator", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "code_client", updatable = false, unique = true, nullable = false)
    private String codeClient;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "code_postal", nullable = false)
    private Integer codePostal;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FactureEntity> factures = new ArrayList<>();

}
