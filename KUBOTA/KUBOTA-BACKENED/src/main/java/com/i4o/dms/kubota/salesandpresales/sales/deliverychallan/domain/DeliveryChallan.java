package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.domain;

import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.dbentities.salespresales.sales.deliverychallan.InsuranceCompanyMaster;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.kubota.salesandpresales.purchase.mahinetransfer.domain.DealerMachineTransfer;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.domain.MachineAllotment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate", "createdBy", "customerMaster", "hibernateLazyInitializer", "dealerMaster"}, allowSetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="sa_delivery_challan")
public class DeliveryChallan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryChallanId;

    @Column(unique = true, length = 21)
    private String deliveryChallanNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    private Date deliveryChallanDate = new Date();

    @NotNull(message = "Delivery Challan Type is Mandatory")
    private String deliveryChallanType;

    private String gatePassNumber;

    @ManyToOne
    @JoinColumn(name = "enquiry_id")
    private Enquiry enquiry;

    @ManyToOne
    @JoinColumn(name = "insurance_master_id")
    private InsuranceCompanyMaster insuranceCompanyMaster;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_master_id")
    private CustomerMaster customerMaster;

    @OneToMany(mappedBy = "deliveryChallan", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<DcMachineDetail> dcMachineDetail;
    
    @OneToMany(mappedBy = "deliveryChallan", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<DcAccessoriesDetail> dcAccessoriesDetails;

    @OneToMany(mappedBy = "deliveryChallan", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<DcDeliverableChecklist> deliverableChecklist;

    @Column(updatable=false)
    private Long branchId;
    
    @ManyToOne
    @JoinColumn(name = "machine_allotment_id")
    private MachineAllotment machineAllotment;

    private String invoiceCustomerName;

    private String customerMobileNumber;

    private String dcCancellationType;

    private String dcCancellationReason;


    private String otherReason;

    @Column(length = 1000)
    private String remarks;

    @Column(length = 1000)
    private String dcCancelRemark;

    private String status = "INVOICE NOT DONE";

    @JsonIgnore
    @Column(updatable=false)
    private Long createdBy;
    
    @Column(updatable=false)
    private Date createdDate = new Date();

    private Date lastModifiedDate ;//= new Date();

    private String vehicleRegistrationNumber;

    @ManyToOne
    @JoinColumn(name = "dealer_machine_transfer_id")
    private DealerMachineTransfer dealerMachineTransfer;

    private Date policyStartDate;

    private Date policyExpiryDate;

    private String customerName;

    private String customerCode;

    private String customerAddress;

    private Integer pinCode;

    private String postOffice;

    private String city;

    private String tehsil;

    private String district;

    private String state;

    private String country;

    @Transient
    private String message;
}
