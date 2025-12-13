package com.i4o.dms.itldis.eamg.part.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * EAMG Part Entity
 */
@Entity
@Table(name = "eamg_part")
@Data
public class EamgPart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "part_no", unique = true)
    private String partNo;
    
    @Column(name = "part_name")
    private String partName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price")
    private Double price;
    
    @Column(name = "sap_part_no")
    private String sapPartNo;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
}
