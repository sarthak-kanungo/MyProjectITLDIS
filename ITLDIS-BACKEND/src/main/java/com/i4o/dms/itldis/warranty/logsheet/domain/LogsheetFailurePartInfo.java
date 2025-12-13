package com.i4o.dms.itldis.warranty.logsheet.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="WA_Logsheet_Failure_Part_Info")
public class LogsheetFailurePartInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    below code is hidden by Suraj
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "spare_part_master_id",referencedColumnName = "itemNo")
//    private SparePartMaster sparePartMaster;
    
    @Transient
    private SparePartMaster sparePartMaster;	//Suraj--20-02-2023
    
    private String sparePartMasterId;	//Suraj--20-02-2023

    private Integer quantity;

    @ManyToOne
    @JsonBackReference
    private WarrantyLogsheet warrantyLogsheet;

    private String failureDescription;		//Suraj--01/11/2022
    
    private String failureCode;		//Suraj--01/11/2022
    
    private Boolean keyPartNumber = false;	//Suraj--23/12/2022
}
