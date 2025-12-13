package com.i4o.dms.itldis.masters.dbentities.warranty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "wa_mt_failure_type")
public class WarrantyMtFailureType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  failureType;

    private String activeStatus;
    
}
