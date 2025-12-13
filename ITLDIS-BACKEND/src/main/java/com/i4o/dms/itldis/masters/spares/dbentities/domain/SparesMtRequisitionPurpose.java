package com.i4o.dms.itldis.masters.spares.dbentities.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SparesMtRequisitionPurpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String requisitionPurpose;

  //  @Column(name="active_status",columnDefinition = "char default Y")
    private Character activeStatus = 'Y';
}
