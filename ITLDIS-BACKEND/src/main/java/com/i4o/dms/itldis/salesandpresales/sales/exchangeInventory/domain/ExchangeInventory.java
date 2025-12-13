package com.i4o.dms.itldis.salesandpresales.sales.exchangeInventory.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.Enquiry;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "SA_OLD_VEH_INV")
public class ExchangeInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oldVehId;
    
    @Column(updatable=false)
    private Long branchId;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enquiryId", referencedColumnName = "id")
    private Enquiry enquiry;
    
    
    @NotNull
    @Column(updatable = false)
    private String brandName;
    
    @NotNull
    @Column(updatable = false)
    private String modelName;
    
    @NotNull
    @Column(updatable = false)
    private Integer modelYear;
    
    @Column(updatable = false)
    private Date invInDate;
    
    @NotNull
    @Column(updatable = false)
    private Double estimatedExchangePrice;
    
    @NotNull
    private String status;
    
    @Column(length = 8, nullable = true)
    private Long buyerId;
    
    @Column(length = 150, nullable = true)
    private String buyerName;
    
    @Column(length = 50, nullable = true)
    private String buyerContactNo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date saledate;
    
    @Column(length = 8, nullable = true)
    private Double sellingprice;
    
    @Column(length = 250, nullable = true)
    private String saleremarks;
    
    @Column(updatable=false)
    private Date createddate = new Date();
    
    @JsonIgnore
    @Column(updatable=false)
    private Long createdby;

    private Date modifieddate = new Date();
    private Long modifiedby;
    
    
}
