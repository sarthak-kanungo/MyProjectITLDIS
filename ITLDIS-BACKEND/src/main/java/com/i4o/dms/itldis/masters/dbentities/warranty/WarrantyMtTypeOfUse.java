package com.i4o.dms.itldis.masters.dbentities.warranty;

import lombok.Getter;
import lombok.Setter;
//import org.graalvm.compiler.lir.StandardOp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="WA_MT_TYPE_OF_USE")
public class WarrantyMtTypeOfUse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeOfUse;

    private String code;

    private String activeStatus="Y";
}
