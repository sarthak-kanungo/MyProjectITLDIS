package com.i4o.dms.kubota.salesandpresales.purchase.mahinetransfer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@JsonIgnoreProperties({"createdBy","dealerMaster","lastModifiedDate"})
@Table(name="SA_MACHINE_TRANSFER_HDR")
public class DealerMachineTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 21,unique = true)
    private String requestNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date requestDate=new Date();
    private String status = "Waitting for Approval";

    @ManyToOne
    @JoinColumn(name = "co_dealer_id")
    private DealerMaster coDealer;

    @ManyToOne
    @JoinColumn(name="dealer_id")
    private DealerMaster dealerMaster;

    @Transient
    private Integer totalQty;

    private Long dealerEmployeeId;
    
    @Column(updatable=false)
    private Long createdBy;
    
    @Column(updatable=false)
    private Date createdDate = new Date();

    private Date lastModifiedDate;
    
    private Long lastModifiedBy;


    @OneToMany(mappedBy = "dealerMachineTransfer",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DealerMachineTransferDetail> dealerMachineTransferDetails;
}
