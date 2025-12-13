package com.i4o.dms.kubota.salesandpresales.enquiry.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
@Table(name = "SA_ENQ_CROP")
public class EnquiryCropGrown
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cropGrown;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Enquiry enquiry;

    private Character isactive='Y';
    private Double acres;
    private Double qtyPerAcre;
    private Double sellingPrice;
    private Date lastupdatedon = new Date();
}
