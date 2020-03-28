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

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "client_code", updatable = false, unique = true, nullable = false)
    private String clientCode;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "fk_id_authority", nullable = false)
    private AuthorityEntity authorityEntity;

    @ManyToOne
    @JoinColumn(name = "fk_id_company", nullable = false, updatable = false)
    private CompanyEntity companyEntity;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InvoiceEntity> invoices = new ArrayList<>();

}
