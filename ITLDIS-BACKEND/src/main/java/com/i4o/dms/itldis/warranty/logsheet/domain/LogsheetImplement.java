package com.i4o.dms.itldis.warranty.logsheet.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.warranty.pcr.domain.WarrantyPcr;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="WA_Logsheet_Implement")
public class LogsheetImplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String implementCategory;

    private String implement;

    private String implementMake;

    private String implementSerialNumber;

    private String gearRation;

    private Integer engineRpm;


    private Double percentOfUsage;

    private Boolean usageFailure=false;

    @ManyToOne
    @JsonBackReference
    private WarrantyLogsheet warrantyLogsheet;


}
