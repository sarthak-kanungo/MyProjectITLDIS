package com.i4o.dms.itldis.inventory.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * SP Order Inventory GRN Details Entity
 * Migrated from: ITLDIS/src/main/java/HibernateMapping/SpOrderInvGrnDetails.java
 * 
 * @author kundan.rastogi
 * @author Migrated to Spring Boot JPA
 */
@Entity
@Table(name = "SP_ORDER_INV_GRN_DETAILS")
@Data
@EqualsAndHashCode(of = "spOrderInvGrnDetailsPK")
public class SpOrderInvGrnDetails implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private SpOrderInvGrnDetailsPK spOrderInvGrnDetailsPK;
    
    @Column(name = "Part_desc", nullable = false)
    private String partdesc;
    
    @Column(name = "Invoice_Qty", nullable = false)
    private Double invoiceQty;
    
    @Column(name = "Received_Qty", nullable = false)
    private Double receivedQty;
    
    @Column(name = "Unit_value", nullable = false)
    private Double unitvalue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GR_NO", referencedColumnName = "GR_NO", insertable = false, updatable = false)
    private SpOrderInvGrn spOrderInvGrn;
    
    public SpOrderInvGrnDetails() {
        this.spOrderInvGrnDetailsPK = new SpOrderInvGrnDetailsPK();
    }
    
    public SpOrderInvGrnDetails(String grNo, String partno) {
        this.spOrderInvGrnDetailsPK = new SpOrderInvGrnDetailsPK(grNo, partno);
    }
}

