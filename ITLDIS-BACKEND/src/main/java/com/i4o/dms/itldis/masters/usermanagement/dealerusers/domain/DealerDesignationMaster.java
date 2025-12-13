package com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Entity
@ToString
@Getter
@Setter
@Table(name="ADM_DEALER_MST_DESIGNATION")
public class DealerDesignationMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long departmentId;

    private String designationcode;
    
    @Column(unique = true)
    private String designation;
    
    private Character activeStatus = 'Y';

    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate;

}