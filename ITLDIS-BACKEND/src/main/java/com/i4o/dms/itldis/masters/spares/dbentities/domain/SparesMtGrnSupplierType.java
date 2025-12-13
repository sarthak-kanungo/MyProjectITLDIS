package com.i4o.dms.itldis.masters.spares.dbentities.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SparesMtGrnSupplierType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String supplierType;

    private Character activeStatus = 'Y';
}
