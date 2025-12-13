package com.i4o.dms.kubota.service.activityproposal.domain;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.products.domain.ProductMaster;
import com.i4o.dms.kubota.masters.service.serviceactivityproposal.domain.ServiceMtActivityType;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SV_ACTIVITY_PROPOSAL")
public class ServiceActivityProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable=false)
    private Date createdDate = new Date();
    
    @Column(length = 21,unique = true)
    private String activityNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
    
    @Column(updatable=false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date activityDate = new Date();
    
    private String status = "Generated";

    @NotBlank(message = "location can't be blank")
    private String location;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fromDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date toDate;

    private Integer noOfDays;

    private Double proposedBudget;

    private Double maxAllowedBudget;

    @NotNull(message = "targeted numbers can't be blank")
    private Integer targetedNumbers;

    private Double approvedAmount;

    private String remarks;

    private Long createdBy;

    @ManyToOne
    @JoinColumn(name = "activity_type_id")
    private ServiceMtActivityType serviceMtActivityType;

    @OneToMany(mappedBy = "serviceActivityProposal", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "a")
    private List<ServiceActivityProposalHeads> serviceActivityProposalHeads;

    @OneToMany(mappedBy = "serviceActivityProposal", cascade = CascadeType.ALL)
    @JsonManagedReference("b")
    private List<ServiceActivityProposalSubActivity> ServiceActivityProposalSubActivity;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;
    
    private Date modifiedDate;
    private Long modifiedBy;
    
    @ManyToMany()
    @JoinTable(name = "sv_activity_proposal_product_mapped",
            joinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<ProductMaster> targetedProducts;

    @NotNull(message = "draft flag can't be blank")
    private Boolean draftFlag;
    
    @Transient
    private String kaiRemark;
}
