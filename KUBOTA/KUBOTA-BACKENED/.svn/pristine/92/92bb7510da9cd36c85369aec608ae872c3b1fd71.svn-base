package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.salesandpresales.purchaseordermachinedetails.domain.PurchaseOrderMachineDetails;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate", "dealerEmployeeMaster", "lastModifiedBy", "dealerMaster"}, allowSetters = true)
@Table(name="SA_PURCHASE_ORDER")
public class PurchaseOrder {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "PO type can`t be blank")
    @NotNull
    @Column(length = 50)
    private String poType;

    @NotBlank(message = "Depot can`t be blank")
    @NotNull
    @Column(length = 50)
    private String depot;

    @Column(length = 21, unique = true)
    private String poNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date poDate = new Date();

    @Column(length = 10)
    private int totalQuantity;

    /* @NotBlank(message = "PO status can`t be blank")
     @NotNull
     @Column(length = 50)*/
    private String poStatus;

    @Column(length = 15)
    private Double totalCreditLimit;

    @Column(length = 15)
    private Double availableLimit;

    @NotNull
    private Double totalOs;

    @NotNull
    private Double currentOs;

    @NotNull
    private Double os0To30Days;

    @NotNull
    private Double os31To60Days;

    @NotNull
    private Double os61To90Days;

    @NotNull
    private Double os90Days;

    @NotNull
    private Double paymentPending;

    @NotNull
    private Double netOs;

    //    @NotNull(message = "Order under process is mandatory")
    private Double orderUnderProcess;

    //    @NotNull(message = "Pending order is mandatory")
    private Double pendingOrder;

    //    @NotNull(message = "Exposure Amount is mandatory")
    private Double exposureAmount;

    @NotNull
    private Double channelFinanceAvailable;

    @NotNull
    private Double basicAmount;

    @NotNull
    private Double totalGstAmount;

    @NotNull
    private Double totalAmount;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Where(clause = "delete_flag=false")
    private List<PurchaseOrderMachineDetails> machineDetails;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date lastModifiedDate = new Date();

    private boolean draftMode;

    //private String branchCode;

    //private String branchName;

    @ManyToOne
    @JoinColumn(name = "dealer_employee_id")
    @JsonBackReference
    private DealerEmployeeMaster dealerEmployeeMaster;

    private Long lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

    @Transient
    private boolean salesAdmin;
    
    @Transient
    private boolean showAllButton;

    private String chequeLeaf;

    private String coveringLetter;

    private String creditApplication;
    
    private String chequeNumber;	//Suraj-10-07-2023
    
    private Date chequeDate;	//Suraj-10-07-2023
    
    private Double chequeAmount;	//Suraj-10-07-2023
    
    private String chequeCopy;	//Suraj-10-07-2023
    
    @Column(updatable=false)
    private Long createdBy;
    
    @Transient
    private String approverRemark;
    
    @JoinColumn(name = "tcs_percent")
    private Double tcsPercent;
    
    @JoinColumn(name = "tcs_value")
    private Double tcsValue;
    
//    @NotNull
//    private String typeOfDelivery;	//Suraj--24-03-2023
}
