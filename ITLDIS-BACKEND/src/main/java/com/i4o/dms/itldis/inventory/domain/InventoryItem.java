package com.i4o.dms.itldis.inventory.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Inventory Item Entity
 * TODO: Map from HibernateMapping entities
 */
@Entity
@Table(name = "inventory_items")
@Data
public class InventoryItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "part_no")
    private String partNo;
    
    @Column(name = "part_name")
    private String partName;
    
    @Column(name = "dealer_code")
    private String dealerCode;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "unit_price")
    private Double unitPrice;
    
    @Column(name = "bin_location")
    private String binLocation;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
    
    // TODO: Add more fields based on HibernateMapping entities
}
