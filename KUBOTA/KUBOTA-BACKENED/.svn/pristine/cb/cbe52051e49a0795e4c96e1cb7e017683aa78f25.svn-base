package com.i4o.dms.kubota.service.mrc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Setter
@Getter
@Table(name="SV_MRC_DISCREPANCY")
public class ServiceMrcDiscrepancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_mrc_id")
    @JsonBackReference
    private ServiceMrc serviceMrc;

    @ManyToOne
    @JoinColumn(name = "spare_part_master_id", referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    @Column
    private Integer quantity;

    @Column(length = 200)
    private String remarks;

    @Column(length = 50)
    private String type;

    @NotNull
    private Boolean raiseComplaint = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleteFlag;

}

