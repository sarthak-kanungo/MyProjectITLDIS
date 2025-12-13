package com.i4o.dms.itldis.salesandpresales.sales.quotation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.Enquiry;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.nio.DoubleBuffer;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate","dealerMaster"}, allowSetters = true)
@Table(name = "SA_QUOTATION")
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long branchId;
    @Column(unique = true,length=21)
    private String quotationNumber = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();
    private String itemNo;
    private String itemDescription;
    private String color;
    private Long qty;
    private Double rate;
    @Transient
    private Double basicPrice;
    private Double grossDiscount;
    private Double amountAfterDiscount;
    private Double igst;
    private Double cgst;
    private Double sgst;
    private Double gstAmount;
    private Double total;
    private Double basicValue;
    private Double discount;
    private Double totalGstAmount;
    private Double charges;
    private Double totalAmount;
    private Double insurance;
    private Double rto;

    @ManyToOne
    @JoinColumn(name = "enquiry_master_id", referencedColumnName = "id")
    private Enquiry enquiry;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "quotationRef")
    private List<QuotationImplements> quotationImplements;

    private Date createdDate = new Date();

    private Long dealerEmployeeId;

    private Long lastModifiedBy;

}
