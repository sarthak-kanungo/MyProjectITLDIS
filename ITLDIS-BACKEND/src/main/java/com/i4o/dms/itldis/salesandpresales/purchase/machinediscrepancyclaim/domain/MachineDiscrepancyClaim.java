package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaintDetail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@JsonIgnoreProperties(value = {"dealerMaster","dealerEmployeeMaster"}, allowSetters = true)
@Entity
public class MachineDiscrepancyClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    private String claimNumber  = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    private String claimStatus;

    @Column(length = 50)
    private String remarks;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(updatable = false)
    private Date claimDate=new Date();

    @Transient
    private List<MachineDescripancyComplaintDetail> machineDiscrepancyComplaintDetail;

    @ManyToOne
    @JoinColumn(name="created_by")
    private DealerEmployeeMaster dealerEmployeeMaster;
    //private MachineDescripancyComplaint

    @ManyToOne
    @JoinColumn(name="dealer_id")
    private DealerMaster dealerMaster;

    @Column(length = 15)
    private boolean draftMode;


}
