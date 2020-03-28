package com.okayo.facture.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Authority")
@Data
@Accessors(chain = true)
public class AuthorityEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, updatable = false)
    private String name;


}
