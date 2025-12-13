package com.i4o.dms.kubota.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.products.domain.ProductMaster;
import com.i4o.dms.kubota.masters.salesandpresales.enquirysource.domain.EnquirySourceMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Getter
@Setter
@Entity
@Table(name = "SM_activity_type_budget_master")
public class ActivityTypeBudgetMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_type", length = 60)
    private String activityType;
    
    @NotBlank(message = "budget type can`t be blank")
    @NotNull
    @Column(length = 50)
    private String budgetType;

    private Double maximumLimit;

    private Integer maximumDayMonth;

    private Double kaiShare;

    private Character activeStatus='Y';

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @ManyToOne
    @JoinColumn(name = "created_by")
    private KubotaEmployeeMaster createdBy;

    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private KubotaEmployeeMaster lastModifiedBy;


    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date lastModifiedDate = new Date();

    @ManyToOne
    @JoinColumn(name="source_master_id")
    private EnquirySourceMaster enquirySourceMaster;


    @ManyToMany()
    @JoinTable(name = "SM_activity_type_budget_head_mapped",
            joinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "head_id", referencedColumnName = "id"))
    private List<MarketingActivityTypeHead> marketingActivityTypeHeads;

}
