package com.i4o.dms.kubota.salesandpresales.enquiry.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReportImages;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "SA_ENQ_HDR")
public class Enquiry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable=false)
    private Long branchId;
    
    @Column(length = 25)
    private String enquiryNumber;

    @Column(length = 50)
    private String enquiryStatus = "OPEN";

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "enquiry date can not be null")
    @Column(updatable=false)
    private Date enquiryDate;
    
    private Double totalReceived;
    
    @Transient
    private String prospectType;
    
    @ManyToOne
    private DealerEmployeeMaster salesPerson;

    @Column(length = 50)
    @Size(max = 50,message = "referral person name can not accept more then 30 character")
    private String referralPersonName;

    @Column(length = 50)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Date validationDate;

    @NotBlank(message = "enquiry type can not be null")
    private String enquiryType;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "followup t ype can not be blank")
    private String followUpType;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "source can not be blank")
    private String source;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "purpose of purchase can not be blank")
    private String purposeOfPurchase;

    @ManyToOne
    //@JsonBackReference(value = "generationActivityNumber")
    @JoinColumn(name = "generation_activity_id")
    private MarketingActivityProposal generationActivityNumber;

    @ManyToOne
    //@JsonBackReference(value = "conversionActivityNumber")
    @JoinColumn(name = "conversion_activity_id")
    private MarketingActivityProposal conversionActivityNumber;

    private String generationActivityType;   

    private Character exchangeReceived;
    
    private Integer exchangeModelYear;

    //doubt
    @Column(length = 50)
    private String retailConversionActivity;

    @Column(length = 50)
    private String conversionActivityType;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "tentative purchase date date can not be null")
    private Date tentativePurchaseDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "next follow up  date can not be null")
    private Date nextFollowUpDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date currentFollowUpDate;
    @Column(length = 50, nullable = false)
    @NotBlank(message = "please fill remarks ")
    @Size(max = 200,message = "remarks can not accept more then 200 character")
    private String remarks;
    @Column(length = 50)
    private String itemNo;
    @Column(length = 200)
    private String itemDescription;
    @Column(length = 50)
    @NotBlank(message = "variant can not be blank")
    private String variant;
    @Column(length = 60)
    @NotBlank(message = "sub model can not be blank")
    private String subModel;
    @Column(length = 60)
    @NotBlank(message = "model can not be blank")
    private String model;
    @Column(length = 60)
    private String series;
    @Column(length = 70)
    private String product;
    @Column(length = 10, nullable = false)
    @NotBlank(message = "exchange required can not be blank ")
    private String exchangeRequired;
    @Column(length = 50)
    private Double exchangeAmount=0.0;//required discussion
    @Column(length = 50)
    private String exchangeBrand;
    private String exchangeModel;
    @Column(length = 50,scale = 3)
    private Double estimatedExchangePrice=0.0;

   /* @Column(length = 50)
    private String prospectCode;
    @Column(length = 50)
    private String prospectType;*/
    @Column(length = 50)
    private String companyName;
    @Column(length = 50)
    private String title;
    @Column(length = 50)
    @NotBlank(message = "first Name can not be null")
    @Size(max = 15,message = "first name can not be more then 15 character")
    private String firstName;
    @Column(length = 50)

    private String middleName;
    @Column(length = 50)
   // @NotBlank(message = "last Name can not be null")
    @Size(max = 15,message = "last name can not be more then 15 character")
    private String lastName;
    @Column(length = 50)
   // @NotBlank(message = "father Name can not be null")
    private String fatherName;
    @Column(length = 50)

    private Integer std;
    @Column(length = 50)
    @Size(max = 10,message = "telephone number can not be more then 10 digits")
    private String telephoneNumber;
    @Column(length = 50)

    private String whatsAppNumber;
    @Column(length = 50)
    private String language;
    @Column(length = 50)
    @Email(message = "please write correct email id")
    @Size(max = 50,message = "email can not be more then 50 character")
    private String emailId;
    @Column(length = 50)
    private String aadharNo;
    @Column(length = 50)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dob;
    @Column(length = 50)
    @NotBlank(message = "mobile number can not be blank")
    @Size(max = 10,min = 10)
    private String mobileNumber;
    //@Column(length = 50,nullable = true)
   // @Size(max = 10,min = 10)
    private String alternateMobileNumber;
    @Column(length = 50)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date anniversaryDate;
//    @Column(length = 50,nullable =true)
//    @Size(max = 15,min = 15,message = "gst max 15 digit")
    private String gstNumber;
    @Column(length = 50)
    @Size(max = 10)
    private String panNumber;
    @Column(length = 100)
    @NotBlank(message = "address can not be blank")
    @Size(max = 100 ,message =  "address can not be blank")
    private String address1;
    @Column(length = 100)
    private String address2;
    @Column(length = 100)
    private String address3;
    @Column(length = 50)
    private Integer pinCode;
    
    private Long pinId;
    @NotBlank(message = "post office can not be blank")
    private String postOffice;
    @Column(length = 50)
    private String city;
    @Column(length = 50)
    private String tehsil;
    @Column(length = 50)
    private String district;
    @Column(length = 50)
    private String state;
    @Column(length = 50)
    private String country;
   @Column(length = 50,nullable = false)
    private Double landSize;

    @OneToMany(mappedBy = "enquiry", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EnquirySoilType> enquirySoilTypes;

    @JsonManagedReference
    @OneToMany(mappedBy = "enquiry", cascade = CascadeType.ALL)
    private List<EnquiryCropGrown> enquiryCropGrows;


    @OneToMany(mappedBy = "enquiry", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EnquiryMachineryDetails> enquiryMachineryDetails;


   /* @ManyToOne
    @JoinColumn(name = "prospect_id", referencedColumnName = "id")
    private ProspectMaster prospectMaster;*/

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerMaster customerMaster;
    private String occupation;

    /*@ManyToOne
    @JoinColumn(name = "dealer_employee_id")
    @JsonProperty("createdBy")
    private DealerEmployeeMaster createdBy;*/
    @JsonIgnore
    @Column(updatable=false)
    private Long createdBy;
    
    /*@ManyToOne
    @JoinColumn(name = "last_modified_by")
    private DealerEmployeeMaster lastModifiedBy;*/

    private Long lastModifiedBy;
    
    @ManyToOne
    private DealerEmployeeMaster transferTo;

    private Long assetValue;
    private String cashLoan;
    @Size(max = 20,message = "financier can not be more the 20")
    private String financier;
    private Boolean appEnquiryFlag;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date financeLoggedInDate;
    @Column(length = 50, scale = 3)
    private Double estimatedFinanceAmount=0.0;
    @Column(length = 50)
    private String financeStatus;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date disbursedDate;
    private Double disbursedFinanceAmount=0.0;
    @Column(length = 50)
    private Double finalExchangePrice=0.0;
    @Column(length = 50)
    private String subsidy;
    @Column(length = 10, scale = 3)
    private Double subsidyAmount=0.0;


    @Column(length = 50)
    private String result;
    @Column(length = 50)
    private String brand;
    @Column(length = 50)
    private String alternateModel;
    @Column(length = 50)
    private String alternateReason;
    @Column(length = 50)
    private String lostDropReason;
    @Column(length = 50)
    private String alternateRemarks;
    @Size(max = 15)
    private String other;
    private Double marginAmount=0.0;
    @Column(updatable=false)
    private Date createdDate = new Date();

    private Date lastModifiedDate = new Date();

    private Boolean allotFlag;

    private Boolean dcFlag;


    /*@ManyToOne
    @JoinColumn(name="dealer_id",updatable = false)
    private DealerMaster dealerMaster;*/
    transient private String salesPersonName;

    transient private Boolean subsidyReceived;
	
//	@Transient 
//	private List<MultipartFile> multipartFileList;
	     
    @OneToMany(mappedBy = "enquiry")
    private List<EnquiryAttachmentsImages> enquiryAttachmentsImages;
    
    private int countUpdate;
    
    private int mobileCountUpdate;
    
}
