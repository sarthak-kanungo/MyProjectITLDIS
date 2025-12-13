package com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@ToString
@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Table(name="ADM_BRANCH_USER")
public class AssignUserToBranchMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(length = 50)
//    private String dealership;
    
    @Column(length = 50)
    private Long dealerEmployeeId;

//    @NotBlank(message = "user Id can`t be blank")
//    @Column(length = 50)
//    private Long userId;
    
    @Column(length = 50)
    private Long branchId;
    
    @Column(length = 50)
    private char isactive;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();
    
    @Column(length = 50)
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate;


}
