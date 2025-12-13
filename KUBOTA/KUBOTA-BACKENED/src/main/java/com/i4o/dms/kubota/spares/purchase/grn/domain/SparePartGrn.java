package com.i4o.dms.kubota.spares.purchase.grn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.accpac.domain.AccpacSparePartInvoice;
import com.i4o.dms.kubota.masters.spares.dbentities.domain.SparesMtSupplier;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.PartyMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.StoreMaster;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
@Setter
@Table(name="SP_PART_GRN")
public class SparePartGrn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Grn Type can not be null")
    @NotEmpty(message = "Grn Type can not be empty")
    private String grnType;

    @NotNull(message = "supplier Type can not be null")
    @NotEmpty(message = "supplier Type can not be empty")
    private String supplierType;

    @Column(unique = true,length = 21)
    private String spareGrnNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @NotNull
    @Column(updatable = false)
   // @FutureOrPresent
//    private Date grnDate = new Date();
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date grnDate;

    //@NotNull
    private Date lastModifiedDate;

    @NotNull(message = "Grn Status can not be null")
    @NotEmpty(message = "Grn Status can not be empty")
    private String grnStatus;

    //@Min(value = 1,message = "Box count must be greater than 1")
    private Integer noOfBoxesSent;

    @Min(value = 1,message = "Box count must be greater than 1")
    private Integer noOfBoxesReceived;

  //  @DecimalMin(value = "1.0")
    private Double receiptValue;

   // @PastOrPresent
   @JsonFormat(pattern = "dd-MM-yyyy")
   private Date goodsReceiptDate;

    @NotNull(message = "Draft Flag is mandatory")
    private Boolean draftFlag;

    //@DecimalMin(value = "1.0")
    private Double basicAmount;

    //@DecimalMin(value = "1.0")
    private Double gstAmount;

    //@DecimalMin(value = "1.0")
    private Double totalGrnAmount;

    private String supplierInvoiceNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date invoiceDate;

    private Double invoiceValue;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SparesMtSupplier supplierName;

    @Column(updatable = false,nullable = false)
    private Long createdBy;
    
    @Column(updatable = false,nullable = false)
    private Date createdDate;
    
    private Long lastModifiedBy;

    @OneToMany(mappedBy = "sparePartGrn",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SparePartGrnItem> sparePartGrnItems;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreMaster store;

    @ManyToOne
    @JoinColumn(name = "acc_pac_spares_invoice_id")
    private AccpacSparePartInvoice invoiceNumber;
    
    private Long coDealerInvoiceId;
    
    private Long branchId;
    @ManyToOne
    @JoinColumn(name = "co_dealer_id")
    private DealerMaster dealerMaster;

    
    private Long vendor_party_id;
    

}
