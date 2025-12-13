package com.i4o.dms.itldis.masters.dbentities.warranty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="WA_MT_FAILURE_MODE")
public class WarrantyMtFailureMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String failureMode;

    private String code;

    private String activeStatus="Y";
}
