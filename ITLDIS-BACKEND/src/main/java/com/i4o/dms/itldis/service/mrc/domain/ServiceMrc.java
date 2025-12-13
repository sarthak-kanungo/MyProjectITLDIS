package com.i4o.dms.itldis.service.mrc.domain;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.accpac.domain.AccPacInvoice;
import com.i4o.dms.itldis.accpac.domain.AccPacInvoicePartDetails;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SV_MRC")
public class ServiceMrc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 21,unique = true)
    private String mrcNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(updatable=false)
    private Date mrcDate= new Date();

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private Boolean draftFlag=false;

    @ManyToOne
    @JoinColumn(name = "accpac_invoice_id")
    private AccPacInvoice accPacInvoice;

    @ManyToOne
    @JoinColumn(name = "accpac_invoice_machine_id")
    private AccPacInvoicePartDetails accPacInvoicePartDetails;

    @Column(updatable=false)
    private Long createdBy;
    
    private Long modifiedBy;
    
    @Column(updatable=false)
    private Date createdDate = new Date();
    
    private Date modifiedDate;
    

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

    @Transient
    private List<ServiceMrcChassisCheckpointInfo> serviceMrcChassisCheckpointInfSet;

    @OneToMany(mappedBy = "serviceMrc", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ServiceMrcDiscrepancy> serviceMrcDiscrepancySet;

    @OneToMany(mappedBy = "serviceMrcId")
    private List<ServiceMrcPhotos> serviceMrcPhotosList;

    @Transient
    private List<MultipartFile> multipartFileList;

 @Override
 public String toString() {
  return "ServiceMrc{" +
          "id=" + id +
          ", mrcNumber='" + mrcNumber + '\'' +
          ", mrcDate=" + mrcDate +
          ", draftFlag=" + draftFlag +
          ", accPacInvoice=" + accPacInvoice +
          ", accPacInvoicePartDetails=" + accPacInvoicePartDetails +
          ", dealerMaster=" + dealerMaster +
          ", serviceMrcChassisCheckpointInfSet=" + serviceMrcChassisCheckpointInfSet +
          ", serviceMrcDiscrepancySet=" + serviceMrcDiscrepancySet +
          ", serviceMrcPhotosList=" + serviceMrcPhotosList +
          ", multipartFileList=" + multipartFileList +
          '}';
 }
}
