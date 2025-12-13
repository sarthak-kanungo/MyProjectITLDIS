package com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.domain;

import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.PartyMaster;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
@Setter
@Table(name = "FM_PAYMENT_RECEIPT")
public class PaymentReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique =true,length = 21)
    private String receiptNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
    private String receiptType;

    private Date receiptDate = new Date();

    private String receiptMode;
    private String chequeDdNo;
    private Date chequeDdDate;
    private Double receiptAmount;
    private Double customerBalance;
    private String chequeDdBank;
    private String transactionNo;
    private Date transactionDate;
    private String cardNo;
    private String cardType;
    private String cardName;
    private String serviceProvides;
    private String remarks;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enquiry_id", referencedColumnName = "id")
    private Enquiry enquiry;

    @ManyToOne
    @JoinColumn(name = "dealer_employee_id")
    private DealerEmployeeMaster dealerEmployeeMaster;

//    @ManyToOne
//    @JoinColumn(name = "last_modified_by")
//    private DealerEmployeeMaster lastModifiedBy;

    /*@ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;*/

    @ManyToOne
    @JoinColumn(name = "party_master_id")
    private PartyMaster partyMaster;

    @ManyToOne
    @JoinColumn(name = "customer_master_id")
    private CustomerMaster customerMaster;

    @ManyToOne
    @JoinColumn(name = "co_dealer_id")
    private DealerMaster coDealer;

    @Column(length = 50)
    private String payeeType;

    private String customerName;

    private String customerAddress1;

    private String customerAddress2;

    private String country;

    private String state;

    private String district;

    private String tehsil;

    private String village;

    private String pinCode;

    private String postOffice;

    private String contactNumber;

    @Column(updatable=false)
    private Long branchId;
    
    private Date lastModifiedDate;
    

    @JoinColumn(name = "last_modified_by")
    private Long lastModifiedBy;
}
