package com.i4o.dms.itldis.inventory.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * SP Order Details EXP Entity
 * Migrated from: ITLDIS/src/main/java/HibernateMapping/SpOrderDetailsEXP.java
 * 
 * @author avinash.pandey
 * @author Migrated to Spring Boot JPA
 */
@Entity
@Table(name = "SP_ORD_DTL_EXP")
@Data
@EqualsAndHashCode(of = "poDetailId")
public class SpOrderDetailsExp implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PO_DTL_ID")
    private Integer poDetailId;
    
    @Column(name = "CUST_PO_NO", nullable = false)
    private String custPoNo;
    
    @Column(name = "POSITION_NO", nullable = false)
    private Integer positionNo;
    
    @Column(name = "PART_NO", nullable = false)
    private String partNo;
    
    @Column(name = "QTY", nullable = false)
    private Integer qty;
    
    @Column(name = "PRICE", nullable = false)
    private Double price;
    
    @Column(name = "STATUS", nullable = false)
    private String status;
    
    @Column(name = "PI_QTY")
    private Integer piQty;
    
    @Column(name = "PENDING_QTY")
    private Integer pendingQty;
    
    @Column(name = "LAST_UPDATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;
    
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_PO_NO", referencedColumnName = "CUST_PO_NO", insertable = false, updatable = false)
    private SpOrderHeaderExp spOrderHeaderExp;
}

