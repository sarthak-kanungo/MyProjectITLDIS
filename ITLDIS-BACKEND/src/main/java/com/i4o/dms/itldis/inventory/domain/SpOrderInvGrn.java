package com.i4o.dms.itldis.inventory.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * SP Order Inventory GRN Entity
 * Migrated from: ITLDIS/src/main/java/HibernateMapping/SpOrderInvGrn.java
 * 
 * @author kundan.rastogi
 * @author Migrated to Spring Boot JPA
 */
@Entity
@Table(name = "SP_ORDER_INV_GRN")
@NamedQueries({
    @NamedQuery(name = "SpOrderInvGrn.findAll", query = "SELECT s FROM SpOrderInvGrn s"),
    @NamedQuery(name = "SpOrderInvGrn.findByGrNo", query = "SELECT s FROM SpOrderInvGrn s WHERE s.grNo = :grNo"),
    @NamedQuery(name = "SpOrderInvGrn.findByDealerCode", query = "SELECT s FROM SpOrderInvGrn s WHERE s.dealerCode = :dealerCode")
})
@Data
@EqualsAndHashCode(of = "grNo")
public class SpOrderInvGrn implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "GR_NO", nullable = false)
    private String grNo;
    
    @Column(name = "GR_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date grDate;
    
    @Column(name = "INVOICE_NO", nullable = false)
    private String invoiceNo;
    
    @Column(name = "INVOICE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    
    @Column(name = "DEALER_CODE", nullable = false)
    private String dealerCode;
    
    @Column(name = "RECEIVED_BY", nullable = false)
    private String receivedBy;
    
    @Column(name = "RECEIVED_ON", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedOn;
    
    @Column(name = "CreatedBy", nullable = false)
    private String createdBy;
    
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column(name = "CUST_PO_NO")
    private String custPoNo;
    
    @OneToMany(mappedBy = "spOrderInvGrn", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpOrderInvGrnDetails> spOrderInvGrnDetailsList;
}

