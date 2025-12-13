package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "SM_activity_claim_head")
public class ActivityClaimHead {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "head name can`t be blank")
    @NotNull
    @Column(length = 255)
    private String headName;

//    @NotNull
//    @Column(length = 15)
//    private Double amount=0.0;

    private Double gstPercent=0.0;


    private Double gstAmount=0.0;

    
    private Double approvedAmount=0.0;
    
    private Double actualClaimAmount=0.0;

//    private Boolean isDelete=false;
//    
//    private String remark;

    private String headImage;

    @Transient
    private MultipartFile image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "marketing_activity_claim_id")
    @JsonBackReference
    private MarketingActivityClaim marketingActivityClaim;
    
    @NotNull
    @Column(length = 100)
    private String vendorName;	//Suraj--10-01-2024
    
    @NotNull
    @Column(length = 100)
    private String billNo;	//Suraj--10-01-2024
    
    @NotNull
    private Date billDate;	//Suraj--10-01-2024
    
    @Transient
    private String billDateTran;	//Suraj--10-01-2024

}
