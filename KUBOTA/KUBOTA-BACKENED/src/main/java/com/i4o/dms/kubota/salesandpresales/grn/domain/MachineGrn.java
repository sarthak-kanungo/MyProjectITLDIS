package com.i4o.dms.kubota.salesandpresales.grn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.accpac.domain.AccPacInvoice;
import com.i4o.dms.kubota.masters.dbentities.grn.Transporter;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"createdDate", "dealerEmployeeMaster", "createdBy", "lastModifiedBy", "hibernateLazyInitializer","dealerMaster"}, allowSetters = true)
public class MachineGrn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grnType;

    private String dmsGrnNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    private String grnStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable=false)
    private Date grnDate = new Date();

    @Column(updatable = false)
    private Date createdDate = new Date();

    private Date lastModifiedDate ;

    private String driverName;

    private String driverMobile;

    private String transporterVehicleNumber;

    @Column(updatable=false)
    private String grnBy;
    
    @Column(updatable=false)
    private Long createdBy;
    
    private Double grossTotalValue;

    @ManyToOne
    @JoinColumn(name = "transporter_id")
    private Transporter transporter;


    @ManyToOne
    @JoinColumn(name = "acc_pac_invoice_id")
    private AccPacInvoice accPacInvoice;

    private Long coDealerInvoiceId;
    private String lrNo;
    @ManyToOne
    @JoinColumn(name = "dealer_employee_id")
    private DealerEmployeeMaster dealerEmployeeMaster;

    private Long lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

    @OneToMany(mappedBy = "machineGrn", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GrnMachineDetails> grnMachineDetails;


}
