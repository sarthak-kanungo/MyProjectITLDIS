package com.i4o.dms.itldis.inventory.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * SP Order Inventory GRN Details Primary Key
 * Migrated from: ITLDIS/src/main/java/HibernateMapping/SpOrderInvGrnDetailsPK.java
 */
@Embeddable
@Data
@EqualsAndHashCode
public class SpOrderInvGrnDetailsPK implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "GR_NO", nullable = false)
    private String grNo;
    
    @Column(name = "Partno", nullable = false)
    private String partno;
    
    public SpOrderInvGrnDetailsPK() {
    }
    
    public SpOrderInvGrnDetailsPK(String grNo, String partno) {
        this.grNo = grNo;
        this.partno = partno;
    }
}

