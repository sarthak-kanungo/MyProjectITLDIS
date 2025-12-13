package com.i4o.dms.kubota.spares.partrequisition.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="SP_PART_ISSUE")
public class SparePartIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //SPIB01/1920/0001
    @NotNull(message = "Part Issue No can not be null")
    private String partIssueNo = "SPI"+System.currentTimeMillis();

    private String issueType;

    private String issueAgainst;

    private String partIssueStatus = "Draft";
    
    @NotNull
    @Column(updatable = false)
    private Date partIssueDate = new Date();

    private Date lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "spare_part_requisition_id")
    private SparePartRequisition sparePartRequisition;

    @ManyToOne
    @JoinColumn(name = "service_job_card_id")
    private ServiceJobCard jobCardNo;

    @ManyToOne
    @JoinColumn(name = "issue_to")
    private DealerEmployeeMaster issueTo;

    @Column(updatable=false)
    private Long branchId;

    @Column(updatable=false)
    private Long createdBy;
    
    @Column(updatable=false)
    private Date createdOn = new Date();

    private Long lastModifiedBy;

    @OneToMany(mappedBy = "sparePartIssue",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SparePartIssueItem> sparePartIssueItems;
}
