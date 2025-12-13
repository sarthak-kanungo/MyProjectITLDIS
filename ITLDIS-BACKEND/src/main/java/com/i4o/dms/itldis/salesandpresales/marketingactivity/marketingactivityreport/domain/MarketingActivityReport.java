package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate","dealerEmployeeMaster","dealerMaster"}, allowSetters = true)
@Getter
@Setter
@Entity
@ToString
@Table(name = "SM_activity_report")
public class MarketingActivityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date actualFromDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date actualToDate;

    @NotNull
    @Column(length = 10)
    private int totalEnquiries;

    @NotNull
    @Column(length = 10)
    private int totalRetails;

    @NotNull
    @Column(length = 10)
    private int totalBookings;


    @ManyToOne
    @JoinColumn(name = "marketing_activity_proposal_id")
    private MarketingActivityProposal marketingActivityProposal;

    @OneToMany(mappedBy = "marketingActivityReport")
    private List<MarketingActivityReportImages> marketingActivityReportImages;

    @Transient
    private List<MultipartFile> multipartFileList;

    @OneToMany(mappedBy = "marketingActivityReportId",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "marketing_activity_report")
    private List<ActivityReportEnquiryDetails> activityReportEnquiryDetails;

    @Transient
    private Long marketingActivityProposalId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();

//    @ManyToOne
//    @JoinColumn(name = "created_by")
//    private DealerEmployeeMaster dealerEmployeeMaster;

//    @ManyToOne
//    @JoinColumn(name = "dealer_id")
//    private DealerMaster dealerMaster;
    private Long dealerId;
	private Long createdBy;



}
