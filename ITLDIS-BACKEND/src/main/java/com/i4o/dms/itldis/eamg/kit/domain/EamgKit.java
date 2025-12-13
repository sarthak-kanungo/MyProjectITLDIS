package com.i4o.dms.itldis.eamg.kit.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * EAMG Kit Entity
 */
@Entity
@Table(name = "eamg_kit")
@Data
public class EamgKit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "kit_no", unique = true)
    private String kitNo;
    
    @Column(name = "kit_name")
    private String kitName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
}
