package com.i4o.dms.kubota.salesandpresales.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
//import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowSetters = true)
@Table(name = "SA_ENQ_FOLLOWUP")
public class EnquiryFollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date currentFollowUpDate;
    private String followUpType;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date nextFollowUpDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date tentativePurchaseDate;
    private String remarks;
    private String enquiryType;//enquiryStatus taken to maintain follow history
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updationDate = new Date();

    @ManyToOne
    @JoinColumn(name = "enquiry_id", referencedColumnName = "id")
    private Enquiry enquiry;

    @Column(updatable=false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdDate = new Date();
    
    @JsonIgnore
    @Column(updatable=false)
    private Long createdBy;
}
