package com.i4o.dms.kubota.service.activityreport.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.service.activityproposal.domain.ServiceActivityProposal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="SV_ACTIVITY_REPORT")
public class ServiceActivityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date actualFromDate;

    private Date actualToDate;

    @NotNull(message = "activity effectiveness can't be blank")
    private String activityEffectiveness;

    private String remarks;

    private Date createdDate= new Date();

    @ManyToOne
    @JoinColumn(name = "service_activity_proposal_id")
    private ServiceActivityProposal serviceActivityProposal;

    @Column(updatable=false)
    private Long createdBy;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

    @OneToMany(mappedBy = "serviceActivityReport",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "c")
    private List<ServiceActivityReportMachineDetails> serviceActivityReportMachineDetails;

    @OneToMany(mappedBy = "serviceActivityReport",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "a")
    private List<ServiceActivityReportServiceDetails> serviceActivityReportServiceDetails;


    @OneToMany(mappedBy = "serviceActivityReport",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "b")
    private List<ServiceActivityReportJobCardDetails> serviceActivityReportJobCardDetails;


    @OneToMany(mappedBy = "serviceActivityReport")
    private List<ServiceActivityReportPhotos> serviceActivityReportPhotos;

    @Transient
    private List<MultipartFile> multipartFileList;
    
    private Date modifiedDate;
    private Long modifiedBy;

}
