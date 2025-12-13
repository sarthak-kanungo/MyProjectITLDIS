package com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.configurations.MessageConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Table(name = "ADM_BRANCH_MASTER")
public class BranchDepotMaster implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = MessageConstants.BRANCH_CODE_NOT_NULL)
    private String branchCode;

    @NotBlank(message = "Branch Name can't be blank")
    @Column(length = 50)
    private String branchName;

    private Character activeStatus = 'Y';

   // private Character depotFlag = 'Y';

    @Column(length = 50)
    @NotBlank(message = "GST Number can't be blank")
    private String gstNo;

    @Column(length = 50)
    private String faxNo;

    @NotBlank(message = "Available Storage Area can't be blank")
    private Double availableStorageArea; //sq.ft

    @Column(length = 50)
    private String contactNumber;

    @Email(message = MessageConstants.EMAIL_NOT_VALID)
    private String contactEmail;

    //branch address

    @NotBlank(message = "Branch Address1 can't be blank")
    @Column(length = 50)
    private String branchAddress1;

    @Column(length = 50)
    private String branchAddress2;

    @Column(length = 50)
    private String branchAddress3;

    @NotBlank(message = "Branch Pin Code can't be blank")
    @Column(length = 50)
    private Integer branchPinCode;

    @Column(length = 50)
    private String branchTehsil;

    @Column(length = 50)
    private String branchCity;

    @Column(length = 50)
    private String branchState;

    @Column(length = 50)
    private String branchCountry;

    @Column(length = 50)
    private String branchStdCode;

    @Column(length = 50)
    private String branchTelephone;

    @Column(length = 50)
    @Email(message = MessageConstants.EMAIL_NOT_VALID)
    private String branchEmail;

    @Column(length = 50)
    private String branchFaxNo;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();
}
