package com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)

public class LocalItemMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String dmsItemNo;

    @Size(min=0,max = 10,message="vendor Item No must be 10 digits")
    @Column(length = 50)
    private String vendorItemNo;

    @Column(length = 50)
    private String itemDesc;

    @Column(length = 50)
    private String vendorDealerCode;

    @Column(length = 50)
    private String hsCode;

    private Double igst;

    private Double cgst;

    private Double sgst;

    private Float purchasePrice;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();
}
