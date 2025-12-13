package com.i4o.dms.kubota.salesandpresales.purchase.machinedescripancycomplaint.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.salesandpresales.grn.domain.GrnMachineDetails;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate","dealerEmployeeMaster","grnMachineDetails"}, allowSetters = true)
@Setter
@Getter
@Entity
public class MachineDescripancyComplaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    @Column(length = 50)
    private String complaintNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(length = 50)
    private String complaintStatus;

    private Boolean draftMode;

    @NotEmpty
    @OneToMany(mappedBy = "machineDescripancyComplaint", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MachineDescripancyComplaintDetail> machineDescripancyComplaintDetails;
    
    @OneToMany(mappedBy = "machineDescripancyComplaint", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MachineDescripancyComplaintPhoto> files;

    @ManyToOne
    @JoinColumn(name = "grn_machine_details_id")
    private GrnMachineDetails grnMachineDetails;

    @ManyToOne
    @JoinColumn(name = "dealer_employee_id")
    private DealerEmployeeMaster dealerEmployeeMaster;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(updatable = false)
    private Date complaintDate=new Date();

    @ManyToOne
    @JoinColumn(name="dealer_id")
    private DealerMaster dealerMaster;

    @Transient
    private List<Map<String, Object>> approvalDetails;
}
