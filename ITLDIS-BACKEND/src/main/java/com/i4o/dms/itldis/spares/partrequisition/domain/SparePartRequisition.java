package com.i4o.dms.itldis.spares.partrequisition.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Setter
@Getter
@Table(name = "SP_PART_REQUISITION")
public class SparePartRequisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "requisition number can't be blank")
    @Size(max = 21)
    private String requisitionNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    private String requisitionPurpose;

    @Column(updatable = false)
    private Date requisitionDate = new Date();

    private String type = "Job Card";

    private String partRequisitionStatus;

    private Date lastModifiedDate;
    
    @Column(updatable=false)
    private Long branchId;

    @Column(updatable=false)
    private Long createdBy;
    @Column(updatable=false)
    private Date createdOn = new Date();

    private Long lastModifiedBy;

    @ManyToOne
    @JoinColumn(name ="service_job_card_id")
    private ServiceJobCard serviceJobCard;

    @OneToMany(mappedBy = "sparePartRequisition",cascade =CascadeType.ALL)
    @JsonManagedReference("sparePartRequisition")
    private List<SparePartRequisitionItem> sparePartRequisitionItem;

}
