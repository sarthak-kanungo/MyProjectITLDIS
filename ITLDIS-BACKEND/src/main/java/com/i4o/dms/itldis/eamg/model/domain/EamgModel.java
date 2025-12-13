package com.i4o.dms.itldis.eamg.model.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * EAMG Model Entity
 */
@Entity
@Table(name = "eamg_model")
@Data
public class EamgModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "model_no", unique = true)
    private String modelNo;
    
    @Column(name = "model_name")
    private String modelName;
    
    @Column(name = "variant")
    private String variant;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
}
