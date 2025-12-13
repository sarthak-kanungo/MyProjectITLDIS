package com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.configurations.MessageConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@ToString
@Entity
@Getter
@Setter
@Table(name="ADM_DEALER_EMP")
public class DealerEmployeeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dealerId;
    
    @Column(length = 50)
    private String employeeCode="EMP/"+ System.currentTimeMillis();

    @Column(length = 50)
    private String title;

    @NotBlank(message = "first name can't be blank")
    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String middleName;

    @Column(length = 50)
    private String lastName;

    @NotBlank(message = "email Id can`t be blank")
    @Email(message = MessageConstants.EMAIL_NOT_VALID)
    @Column(unique = true,length = 50)
    private String emailId;

    @NotBlank(message = "mobile No can`t be blank")
    @Column(unique = true,length = 50)
    private String mobileNo;

    private Long departmentId;

    private Long designationId;

    private Long reportingEmployeeId;

    //address
    @NotBlank(message = "address1 can`t be blank")
    @Column(length = 50)
    private String address1;

    @Column(length = 50)
    private String address2;

    @Column(length = 50)
    private String address3;

    @NotBlank(message = "pin Code can`t be blank")
    @Column(length = 50)
    private String pinCode;

    @NotBlank(message = "locality can`t be blank")
    @Column(length = 50)
    //postOffice
    private String locality;

    @Column(length = 50)
    private String tehsil;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String country;

    @Column(length = 50)
    private String district;
    
    @Column(length = 50)
    private String activeStatus;

    @Column(length = 50)
    private String alternateMobileNo;

    @Column(length = 50)
    private String emergencyContactName;

    @Column(length = 50)
    private String emergencyContactNo;
    
    @NotBlank(message = "division can`t be blank")
    @Column(length = 50)
    private String division;

    @Column(length = 50)
    private String licenceType;

    @Column(length = 50)
    private String drivingLicenceNo;

    @Column(length = 50)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date drivingLicenceExpiryDate;

    //Employee
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "birth Date can`t be blank")
    @Column(length = 50)
    private Date birthDate;

    @Column(length = 50)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date anniversaryDate;

    @NotBlank(message = "highest Qualification can`t be blank")
    @Column(length = 50)
    private String highestQualification;

    @Column(length = 50)
    private String maritalStatus;

    @Column(length = 50)
    private String bloodGroup;

    @Column(length = 50)
    private String sex;

    @NotNull(message = "joining Date can`t be blank")
    @Column(length = 50)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date joiningDate;

    @Column(length = 50)
    private String latestSalary;

    @Column(length = 50)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date leavingDate;

    @Column(length = 50)
    private String pfNo;

    @Column(length = 50)
    private String panNo;

    @Column(length = 50)
    private String esiNo;

    @Column(length = 50)
    private String bankAccountNo;

    @Column(length = 50)
    private String bankName;

    @Column(length = 50)
    private String bankBranch;

    @Column(length = 50)
    private String ifsCode;		//Suraj--07/12/2022
    
   // AUTHORIZED FOR DISCOUNT need to discuss
/*
    //file upload
    @Column(length = 50)
    private String drivingLicencePath;

    @NotBlank(message = "Employee Photo can`t be blank")
    @Column(length = 50)
    private String photoPath;

    @NotBlank(message = "Gov Id Proof can't be blank")
    @Column(length = 50)
    private String govIdPath;

    @Transient
    @Column(length = 50)
    private MultipartFile drivingLicenceDocument;

    @Transient
    @Column(length = 50)
    private MultipartFile employeePhotoDocument;

    @Transient
    @Column(length = 50)
    private MultipartFile govIdProofDocument;
*/
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();

    private Long createdBy;

    private Long lastModifiedBy;

    private String approvalStatus;	//Suraj--14/12/2022
    
    private Date approvedDate;	//Suraj--14/12/2022
    
    private String lastApprovedBy;	//Suraj--14/12/2022
    
    private String remark;	//Suraj--14/12/2022
    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date preFromDate;	//Suraj--08/02/2023
    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date preToDate;	//Suraj--08/02/2023
    
    private Long totalExperience;	//Suraj--08/02/2023
    
    private String aadharNo;	//Suraj--08/02/2023
    
}

