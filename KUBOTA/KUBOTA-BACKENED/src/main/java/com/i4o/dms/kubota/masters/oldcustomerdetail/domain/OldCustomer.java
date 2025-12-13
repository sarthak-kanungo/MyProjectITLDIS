package com.i4o.dms.kubota.masters.oldcustomerdetail.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "old_customer")
public class OldCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oldCustomerCode;
    private String customerType;
    private String companyName;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fatherName;
    private Integer std;
    private String telephoneNo;
    private String whatsAppNo;
    private Date dob;
    private String emailId;
    private String aadharNo;
    private String language;
    private String mobileNumber;
    private String alternateMobileNo;
    private Date anniversaryDate;
    private String gstNo;
    private String pancardNo;
    private String address1;
    private String address2;
    private String address3;
    private Integer pinCode;
    private String PostOffice;
    private String city;
    private String tehsil;
    private String district;
    private String state;
    private String country;
    private String occupation;
    private Double landInAcres;
    private String manufacture;
    private String modelCode;
    private String modelDescription;
    private String chassisNo;
    private String registrationNo;

    @OneToMany(mappedBy = "oldCustomer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CustomerCropGrown> customerCropGrown;

    @OneToMany(mappedBy = "oldCustomer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CustomerSoilType> customerSoilType;

    @OneToMany(mappedBy = "oldCustomer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CustomerMachineryDetails> customerMachineryDetails;

}
