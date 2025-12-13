package com.i4o.dms.itldis.masters.dbentities.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.i4o.dms.itldis.masters.products.domain.ModelMaster;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="sv_Mt_Frs_Code")
public class ServiceMtFrsCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String jobCodeNo;

    @Column(length = 300)
    private String description;

    private Float time;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelMaster modelMaster;

    private String activeStatus;

}
