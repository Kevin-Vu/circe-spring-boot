package com.okayo.facture.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "client")
@Data
@Accessors(chain = true)
public class TaxeEntity {

    @Id
    @SequenceGenerator(name = "taxe_generator", sequenceName = "taxe_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taxe_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", scale = 2, nullable = false)
    private Float amount;

    @Column(name = "expirity_date", nullable = false)
    private Timestamp expirityDate;

    @ManyToOne
    @JoinTable(name = "product_type_taxe",
            joinColumns = @JoinColumn(name = "fk_id_taxe" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_id_product_type", referencedColumnName = "id"))
    private ProductTypeEntity productTypeEntity;
}
