package com.i4o.dms.kubota.masters.usermanagement.user.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "ADM_USER")
@JsonIgnoreProperties(value = {"kubotaEmployeeMaster", "dealerEmployeeMaster"}, allowSetters = true)
public class LoginUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userTypeId;
    
    @Column(unique = true, nullable = false)
    private String userName;

    private String password;

    private Character isactive;

    private String loginIdStatus = "ACTIVE";

    private Long dealerEmployeeId;
 
    private Long kubotaEmployeeId;
    
    private Date lastpasswordresetdate;

    private Character isuserlogged;

    private Long createdby;
    
    private Date createddate;

    private Long modifiedby;
    
    private Date modifieddate;
    
}
