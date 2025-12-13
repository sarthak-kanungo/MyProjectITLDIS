package com.i4o.dms.kubota.salesandpresales.sales.allotment.domain;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"createdDate", "createdBy", "deAllocatedBy", "hibernateLazyInitializer", "dealerMaster"}, allowSetters = true)
@Table(name="SA_MACHINE_ALLOTMENT")
public class MachineAllotment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 21)
    private String allotmentNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
    @Column(updatable = false)
    private Long branchId;
    @Column(updatable = false)
    private Long enquiryId;

    private Long dealerMachineTransferId;
    
    private String mobileNumber;

    private String customerName;

    @Column(length = 30)
    private String customerCode;

    private String country;

    private String state;

    private String district;

    private String pincode;
    
    private Long pinId;

    private String postOffice;

    private String city;

    private String tehsil;

    private String address;

    private Boolean onlyImplementFlag = false;

    @Column(updatable = false)
    private Date createdDate = new Date();
    
    @Column(updatable = false)
    private Long createdBy;
    
    private Date lastModifiedDate = new Date();
    
    private Long lastModifiedBy;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machineAllotment")
    @JsonManagedReference
    private List<MachineAllotmentDetail> machineAllotmentDetails;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date allotmentDate=new Date();

    private String deAllotReason;

    private Date deAllotmentDate;

    private Long deAllocatedById;

    private Boolean deAllotFlag = false;
    
    @Column(updatable = false)
    private Long dealerEmployeeId;

}
