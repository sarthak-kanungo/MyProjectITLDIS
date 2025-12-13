package com.i4o.dms.kubota.spares.partrequisition.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="SP_PART_RETURN")
public class SparePartReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //PRRB01/1920/0001
    private String partReturnNo = "PRR-" + System.currentTimeMillis();

    @Column(updatable = false)
    private Date returnDate = new Date();

    private Date lastModifiedDate;

    private String reasonForReturn;

    @Column(updatable=false)
    private Long branchId;

    @Column(updatable=false)
    private Date createdDate = new Date();
    
    @Column(updatable=false)
    private Long createdBy;

    @ManyToOne
    @JoinColumn(name = "received_by")
    private DealerEmployeeMaster dealerEmployeeMaster;

    private Long lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "spare_part_requisition_id")
    private SparePartRequisition sparePartRequisition;

    @ManyToOne
    @JoinColumn(name = "service_job_card_id")
    private ServiceJobCard serviceJobCard;

    @OneToMany(mappedBy = "sparePartReturn", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SparePartReturnItem> sparePartReturnItems;

    //requested by
    //received by
}
