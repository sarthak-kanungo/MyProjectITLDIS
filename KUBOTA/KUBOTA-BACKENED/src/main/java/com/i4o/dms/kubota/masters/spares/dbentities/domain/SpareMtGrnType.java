package com.i4o.dms.kubota.masters.spares.dbentities.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SpareMtGrnType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String grnType;

    private Character activeStatus = 'Y';
}
