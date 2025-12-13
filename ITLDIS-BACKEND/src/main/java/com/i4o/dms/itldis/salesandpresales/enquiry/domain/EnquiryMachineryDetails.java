package com.i4o.dms.itldis.salesandpresales.enquiry.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "SA_ENQ_MACHINERY_DTL")
public class EnquiryMachineryDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private  String brand;
    @Column(length = 60)
    private  String model;
    @Column(length = 10)
    private  String yearOfPurchase;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Enquiry enquiry;
    private Boolean deleteFlag=false;



}
