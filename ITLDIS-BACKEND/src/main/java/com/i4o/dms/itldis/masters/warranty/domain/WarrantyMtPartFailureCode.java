package com.i4o.dms.itldis.masters.warranty.domain;

import com.i4o.dms.itldis.masters.products.domain.ProductMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="Wa_Mt_Part_Failure_Code")
public class WarrantyMtPartFailureCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String failureGroupCode;

    @ManyToOne
    @JoinColumn(name = "product_master_id")
    private ProductMaster productMaster;

    private String groups;

    private String code;

    private String complaintDescription;

    private String activeStatus;


}
