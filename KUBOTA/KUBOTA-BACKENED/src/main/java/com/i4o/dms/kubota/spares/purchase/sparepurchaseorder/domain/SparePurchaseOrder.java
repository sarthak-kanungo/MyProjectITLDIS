package com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.spares.dbentities.domain.SparesMtPurchaseOrderOrderType;
import com.i4o.dms.kubota.masters.spares.dbentities.domain.SparesMtSupplier;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
@Setter
@Table(name = "SP_PURCHASE_ORDER")
public class SparePurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 21)
    private String purchaseOrderNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Column(updatable=false)
    @NotNull(message = "PO Date is mandatory")
    private Date purchaseOrderDate = new Date();

    @ManyToOne
    @JoinColumn(name = "order_type_id", updatable=false)
    @NotNull(message = "order Type is mandatory")
    private SparesMtPurchaseOrderOrderType orderType;

    @NotNull(message = "supplier type is mandatory")
    @Column(length = 50)
    private String supplierType;

    @Column(length = 50)
    @NotNull(message = "Freight Borne By is mandatory")
    private String freightBorneBy;

    @Column(length = 30)
    private String transportMode;

    @Column(length = 50)
    private String transporter;

    @Column(length = 50)
    private Double creditLimit;

    @Column(length = 50)
    private Double currentOutStanding;

    @Column(length = 50)
    private Double overduesOutStanding;

    @Column(length = 50)
    private Double paymentUnderProcess;

    @Column(length = 50)
    private Double ordersUnderProcess;

    @Column(length = 50)
    private Double availableLimit;

    @Column(length = 50)
    private Double netAmountPayable;

    @Column(length = 250)
    private String remarks;

    @NotNull(message = "Draft Flag is mandatory")
    private Boolean draftFlag;

    @OneToMany(mappedBy = "sparePurchaseOrder", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<SparePurchaseOrderItem> sparePurchaseOrderPartDetail;

    @Column(updatable=false)
    private Long createdBy; 
    
    @Column(updatable=false)
    private Date createdDate = new Date();

    private Long lastModifiedBy;
    
    private Date lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    @NotNull(message = "Dealer master is mandatory")
    private DealerMaster dealerMaster;

    @Column(length = 50)
    private Double totalBaseAmount;

    @Column(length = 50)
    private Double totalPoAmount;

    @ManyToOne
    @JoinColumn(name = "supplier_master_id")
    private SparesMtSupplier sparesMtSupplier;
    
    private Long vendorPartyId;

    @Column(length = 50)
    @NotNull(message = "PO status is mandatory")
    private String purchaseOrderStatus;

    @Column(length = 50)
    private String priceType;

    @ManyToOne
    @JoinColumn(name = "co_dealer_id")
    private DealerMaster coDealerMaster;
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "job_card_id",updatable=false)
    private ServiceJobCard serviceJobCard;

    private Boolean grnDoneFlag = false;

    private Double transportationCharges;
    
    @Transient
    private Long opsId;

}
