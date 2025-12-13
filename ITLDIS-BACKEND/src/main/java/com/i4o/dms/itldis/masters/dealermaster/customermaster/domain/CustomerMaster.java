package com.i4o.dms.itldis.masters.dealermaster.customermaster.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectCropGrown;
import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectMachineryDetail;
import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectSoilType;
import com.i4o.dms.itldis.masters.sales.domain.Sales;
import com.i4o.dms.itldis.warranty.logsheet.domain.WarrantyLogsheet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"createdBy","createdDate","lastModifiedBy","lastModifiedDate"},allowSetters = true)
@Table(name = "SA_CUSTOMER_HDR")
public class CustomerMaster implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7651697638422514430L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String customerCode= "Cust-"+System.currentTimeMillis();
    private String recordType;
    private  String customerType;
    private String companyName;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fatherName;
    private Integer std;
    @JsonProperty("telephoneNumber")
    private String telephoneNo;
    @JsonProperty("whatsAppNumber")
    private String whatsAppNo;
    private String language;
    private String emailId;
    private String aadharNo;
    private Date dob;

    //column should be unique
    private String mobileNumber;
    @JsonProperty("alternateMobileNumber")
    private String alternateMobileNo;
    private Date anniversaryDate;
    @JsonProperty("gstNumber")
    private String gstNo;
    @JsonProperty("panNumber")
    private String pancardNo;
    private String address1;
    private String address2;
    private String address3;
    private Integer pinCode;
    private Long pinId;
    private String postOffice;
    private String city;
    private String tehsil;
    private String district;
    private String state;
    private String country;
    private String occupation;
    @JsonProperty("landSize")
    private Double landInAcres;
    private String chassisNo;

    @OneToMany(mappedBy = "customerMaster",cascade = CascadeType.ALL)
    private List<ProspectSoilType> prospectSoilTypes;

    @OneToMany(mappedBy = "customerMaster",cascade = CascadeType.ALL)
    private List<ProspectCropGrown> prospectCropNames;

    @OneToMany(mappedBy = "customerMaster",cascade = CascadeType.ALL)
    private List<ProspectMachineryDetail> prospectMachineryDetails;

   /* @OneToMany(mappedBy = "customerMaster",cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<Sales> sales;*/

    @Transient
    private List<Map<String, Object>> vehicleDetails;
    
    @Column(updatable=false)
    private Long createdBy;
    private Long lastModifiedBy;
    @Column(updatable=false)
    private Date createdDate=new Date();
    private Date lastModifiedDate;

    private Integer editCount;	//Suraj 06-10-2022
    
}
