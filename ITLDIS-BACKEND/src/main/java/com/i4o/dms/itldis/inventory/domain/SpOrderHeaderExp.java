package com.i4o.dms.itldis.inventory.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * SP Order Header EXP Entity
 * Migrated from: ITLDIS/src/main/java/HibernateMapping/SPOrderHeaderEXP.java
 * 
 * @author avinash.pandey
 * @author Migrated to Spring Boot JPA
 */
@Entity
@Table(name = "SP_ORD_HDR_EXP")
@Data
@EqualsAndHashCode(of = "custPoNo")
public class SpOrderHeaderExp implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "CUST_PO_NO", nullable = false)
    private String custPoNo;
    
    @Column(name = "ORD_TYPE", nullable = false)
    private String ordType;
    
    @Column(name = "DEALER_CODE", nullable = false)
    private String dealerCode;
    
    @Column(name = "DEALER_REF_NO", nullable = false)
    private String dealerRefNo;
    
    @Column(name = "CUST_PO_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date custPoDate;
    
    @Column(name = "COMMENTS")
    private String comments;
    
    @Column(name = "ENGINE_NO")
    private String engineNo;
    
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    
    @Column(name = "MODEL_NO")
    private String modelNo;
    
    @Column(name = "JOB_CARD_NO")
    private String jobCardNo;
    
    @Column(name = "DELIVERY_TERMS", nullable = false)
    private String deliveryTerms;
    
    @Column(name = "PAYMENT_TERMS", nullable = false)
    private String paymentTerms;
    
    @Column(name = "INCO_TERMS", nullable = false)
    private String incoTerms;
    
    @Column(name = "CONSIGNEE_CODE", nullable = false)
    private String consigneeCode;
    
    @Column(name = "CONSIGNEE_NAME")
    private String consigneeName;
    
    @Column(name = "CONSIGNEE_ADDRESS", nullable = false)
    private String consigneeAddress;
    
    @Column(name = "CONSIGNEE_COUNTRY", nullable = false)
    private String consigneeCountry;
    
    @Column(name = "DISCHARGE_PORT", nullable = false)
    private String dischargePort;
    
    @Column(name = "DESTINATION_PLACE", nullable = false)
    private String destinationPlace;
    
    @Column(name = "PRE_CARRIAGE_BY")
    private String precarriageBy;
    
    @Column(name = "PLACE_PRE_CARRIER")
    private String placePreCarrier;
    
    @Column(name = "TOTAL_VALUE", nullable = false)
    private Double totalValue;
    
    @Column(name = "STATUS", nullable = false)
    private String status;
    
    @Column(name = "IS_DOCUMENT_UPLOAD", nullable = false)
    private String isDocumentUpload;
    
    @Column(name = "DOCUMENT_NAME", nullable = false)
    private String documentName;
    
    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;
    
    @Column(name = "CreatedOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column(name = "HO_PROCESS_STATUS")
    private String hoProcessStatus;
    
    @Column(name = "HO_REMARKS", nullable = false)
    private String hoRemarks;
    
    @Column(name = "LAST_UPDATED_ON", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;
    
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    
    @Column(name = "ORDER_PRICELIST_CODE", nullable = false)
    private String priceListCode;
    
    @Column(name = "ORDER_CURRENCY", nullable = false)
    private String currency;
    
    @OneToMany(mappedBy = "spOrderHeaderExp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpOrderDetailsExp> spOrderDetailsExpList;
}

