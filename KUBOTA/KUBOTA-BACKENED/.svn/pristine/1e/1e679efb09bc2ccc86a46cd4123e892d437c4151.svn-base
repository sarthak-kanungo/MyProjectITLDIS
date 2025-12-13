package com.i4o.dms.kubota.salesandpresales.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Setter
@Getter
@Table(name = "SA_ENQ_SOIL_TYPE")
public class EnquirySoilType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String soilType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Enquiry enquiry;
    private Boolean deleteFlag=false;
}
