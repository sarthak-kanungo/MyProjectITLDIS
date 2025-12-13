package com.i4o.dms.itldis.salesandpresales.grn.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.products.domain.MachineMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "machine_grn_detail")
public class GrnMachineDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemNo; //remove this field in next build front changes accordingly

    private String itemDescription;  //remove this field in next build front changes accordingly

    private Integer invoiceQuantity;

    private String chassisNo;

    private String engineNo;

    private Double unitPrice;

    private Double totalValue;

    private Integer receiptQuantity;
    
    private Integer descripancyQuantity;

    private String remarks;
    
    private Double assessableAmount;
    
    private Double gstAmount;

    @ManyToOne
    @JoinColumn(name = "machine_grn_id")
    @JsonBackReference
    private MachineGrn machineGrn;

    @ManyToOne
    @JoinColumn(name="machine_master_id")
    private MachineMaster machineMaster;

    @Transient
    private String category;
}
