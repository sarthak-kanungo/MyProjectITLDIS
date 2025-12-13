package com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.itldis.configurations.MessageConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Getter
@Setter
@Entity
public class DealerBranchAndSubDealerMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //subDealer details
    @NotBlank(message = "category can`t be blank")
    @Column(length = 50)
    private String category;

    @Column(length = 50)
    private String branchCode;

    @NotBlank(message = "sub Dealer Name can`t be blank")
    @Column(length = 50)
    private String subDealerName;

    @NotBlank(message = "location can`t be blank")
    @Column(length = 50)
    private String location;

    private Double subDealerGstNumber;

    private Double subDealerPanNumber;

    @Column(length = 50)
    private String subDealerAdharCardNumber;

    @Column(length = 50)
    private String contactPerson;

    @NotBlank(message = "title can`t be blank")
    @Column(length = 50)
    private String title;

    @NotBlank(message = "first Name can`t be blank")
    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String middleName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)
    private String designation;

    @NotBlank(message = "mobile Number can`t be blank")
    @Column(length = 50)
    private String mobileNumber;

    @Column(length = 50)
    @Email(message = MessageConstants.EMAIL_NOT_VALID)
    private String emailId;

    //address Details
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
    private String locality;

    @Column(length = 50)
    private String tehsil;

    @Column(length = 50)
    private String city;

    //need to discuss
    //@Column(length = 50)
    //private String district;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String country;

    @Column(length = 50)
    private String telephoneNo;

    //repeat email field-need to discuss
    // @Column(length = 50)
    //private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();


}
