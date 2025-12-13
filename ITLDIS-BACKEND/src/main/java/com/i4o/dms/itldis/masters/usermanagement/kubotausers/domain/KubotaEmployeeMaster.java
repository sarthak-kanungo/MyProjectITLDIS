package com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.i4o.dms.itldis.configurations.MessageConstants;
import com.i4o.dms.itldis.masters.dbentities.user.DesignationHierarchy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ADM_HO_USER")
public class KubotaEmployeeMaster implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2245968142620780201L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "Employee Code can't be blank")
    private String employeeCode;

    @NotBlank(message = "Employee Name can't be blank")
    private String employeeName;

    private Character activeStatus;

    @NotNull
    @Size(min=10,max = 10,message="Contact Number must be 10 digits")
    @Column(unique = true)
    private String contactNo;

    @NotEmpty(message = "email must not be empty")
    @Email(message = MessageConstants.EMAIL_NOT_VALID)
    @Column(unique = true)
    private String emailId;

    private Long hoDepartmentId;
    
    private Long hoDesignationId;
    
    private Long hoDesignationLevelId;
    
    private Long reportingUserId;

    private String kaiBranch;
    
    private Boolean managementAccess = false;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();

    private Long createdBy;

    private Long lastModifiedBy;
    
}
