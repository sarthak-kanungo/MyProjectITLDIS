package com.i4o.dms.itldis.eamg.group.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * EAMG Group Entity
 * TODO: Map from HibernateMapping entities
 */
@Entity
@Table(name = "eamg_group")
@Data
public class EamgGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "group_no", unique = true)
    private String groupNo;
    
    @Column(name = "group_name")
    private String groupName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "sequence")
    private Integer sequence;
    
    @Column(name = "f_code")
    private String fCode;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
    
    // TODO: Add more fields based on HibernateMapping entities
}
