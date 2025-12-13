package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@JsonIgnoreProperties(value = {"createdDate","lastModifiedDate"}, allowSetters = true)
@Getter
@Setter
@Entity
@Table(name = "SA_channel_finance_indent")
public class ChannelFinanceIndent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_finance_id")
    private Long channelFinanceId;

    @NotBlank(message = "bank can`t be blank")
    @NotNull
    @Column(length = 50)
    private String bankName;

    @Column(length = 21,unique = true)
    private String indentNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(length = 10)
    private int numberOfDays;

    @Column(name = "\"limit\"", length = 15)
    private double limit;

    @Column(length = 15)
    private double utilized;

    @Column(length = 15)
    private double available;

    @Column(length = 15)
    private double indentAmount;

//    @Column(length = 50)
    @Transient
    private String dealerCode;

    @Column(length = 50)
    private String flexiLoanAccountNumber;

    @Column(length = 50)
    private String dealerCategory;

    @OneToMany(mappedBy = "channelFinanceIndent",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ChannelFinanceIntentDetail> channelFinanceIntentDetail;

/*    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();*/

    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date indentDate=new Date();

    @ManyToOne
    @JoinColumn(name = "dealer_employee_id")
    @JsonBackReference
    private DealerEmployeeMaster dealerEmployeeMaster;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;
    
    @Column(name = "created_by",updatable = false)
    private Long createdBy;
    
    @Column(updatable = false)
    private Date createdDate=new Date();
    
    @Column(name = "indent_status")
    private String indentStatus = "Under Process";
    
    @Column(name = "approver_remarks")
    private String approverRemarks;
        
    @Column(name = "last_modified_by")
    private Long last_modified_by;
    
    @Column(updatable = false)
    private Date lastModifiedDate=new Date();

    @Transient
    private List<String> approvalButtonList;
}
