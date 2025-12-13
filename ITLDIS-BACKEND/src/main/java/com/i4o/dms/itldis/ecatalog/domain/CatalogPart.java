package com.i4o.dms.itldis.ecatalog.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Catalog Part Entity
 * TODO: Map from HibernateMapping entities (CatPart, Partmaster, etc.)
 */
@Entity
@Table(name = "catalog_parts")
@Data
public class CatalogPart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "part_no", unique = true)
    private String partNo;
    
    @Column(name = "part_name")
    private String partName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "price")
    private Double price;
    
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
    
    // TODO: Add more fields based on HibernateMapping entities
}
