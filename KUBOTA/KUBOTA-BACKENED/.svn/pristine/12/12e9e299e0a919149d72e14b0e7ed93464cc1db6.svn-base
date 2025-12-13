package com.i4o.dms.kubota.masters.spares.dbentities.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SpareMtGrnStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String grnStatus;

    private Character activeStatus = 'Y';
}
