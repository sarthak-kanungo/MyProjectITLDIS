package com.i4o.dms.itldis.service.serviceoptions.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Service Option Entity
 */
@Entity
@Table(name = "service_options")
@Data
public class ServiceOption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "option_id", unique = true)
    private String optionId;
    
    @Column(name = "option_name")
    private String optionName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "dealer_code")
    private String dealerCode;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
}
