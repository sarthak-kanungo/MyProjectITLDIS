package com.i4o.dms.itldis.warranty.pcr.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name="WA_PCR_IMPLEMENTS")
public class WarrantyPcrImplements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "implement Category can't be null")
    private String implementCategory;

    @NotNull(message = "implement can't be null")
    private String implement;

    @NotNull(message = "implement make can't be null")
    private String implementMake;

    private String implementSerialNumber;

    private String gearRatio;

    private Integer engineRpm;

    private Double percentOfUsage;

    private Boolean usageFailure=false;

    @ManyToOne
    @JsonBackReference
    private WarrantyPcr warrantyPcr;






}
