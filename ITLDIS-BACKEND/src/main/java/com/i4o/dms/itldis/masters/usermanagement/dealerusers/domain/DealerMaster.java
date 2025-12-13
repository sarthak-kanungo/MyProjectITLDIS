package com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.itldis.configurations.MessageConstants;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Entity
@Getter
@Setter
@Table(name="ADM_dealer_master")
public class DealerMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "dealer Code can`t be blank")
    @Column(length = 50, unique = true)
    private String dealerCode;

    private Character stockist;

    @NotBlank(message = "dealer Name can`t be blank")
    @Column(length = 50)
    private String dealerName;

    //@NotBlank(message = "active Status can`t be blank")
    private Character activeStatus;

    @NotBlank(message = "dealer Firm Type can`t be blank")
    @Column(length = 50)
    private String dealerFirmType;

    @NotBlank(message = "gst No can`t be blank")
    @Column(length = 50)
    private String gstNo;

    @NotBlank(message = "pan No can`t be blank")
    @Column(length = 50)
    private String panNo;

    @Column(length = 50)
    private String state;

    private String logo;

    //for upload logo document
    @Transient
    @Column(length = 50)
    private MultipartFile logoDocument;

    @NotBlank(message = "email Id can`t be blank")
    @Email(message = MessageConstants.EMAIL_NOT_VALID)
    @Column(length = 50, unique = true)
    private String emailId;

    @NotBlank(message = "area Level can`t be blank")
    @Column(length = 50)
    private String areaLevel;

    //May be multiple and need to check with kubota
    @Column(length = 50)
    private String allocatedTerritory;

    //Bank details
    @NotBlank(message = "bank Name can`t be blank")
    @Column(length = 50)
    private String bankName;

    @NotBlank(message = "bank Branch Name can`t be blank")
    @Column(length = 50)
    private String bankBranchName;

    @NotBlank(message = "current Account No can`t be blank")
    @Column(length = 50)
    private String currentAccountNo;

    @NotBlank(message = "ifsc Code can`t be blank")
    @Column(length = 50)
    private String ifscCode;

    @NotBlank(message = "micr Code can`t be blank")
    @Column(length = 50)
    private String micrCode;

    private Double creditLimit;

    private Character subsidyDealer;

    //billing address
    @NotBlank(message = "billing Address1 can`t be blank")
    @Column(length = 50)
    private String billingAddress1;

    @NotBlank(message = "billing Address2 can`t be blank")
    @Column(length = 50)
    private String billingAddress2;

    @NotBlank(message = "billing Address3 can`t be blank")
    @Column(length = 50)
    private String billingAddress3;

    @Column(length = 50)
    private String billingAddress4;

    @Column(length = 50)
    private String billingPostOffice;

    @NotBlank(message = "billing Pin code can`t be blank")
    @Column(length = 50)
    private Integer billingPincode;

    @Column(length = 50)
    private String billingTehsil;

    @Column(length = 50)
    private String billingCity;

    @Column(length = 50)
    private String billingDistrict;

    @Column(length = 50)
    private String billingState;

    @Column(length = 50)
    private String billingCountry;

    //showroom Address
    @NotBlank(message = "showroom Address1 can`t be blank")
    @Column(length = 50)
    private String showroomAddress1;

    @NotBlank(message = "showroom Address2 can`t be blank")
    @Column(length = 50)
    private String showroomAddress2;

    @NotBlank(message = "showroom Address3 can`t be blank")
    @Column(length = 50)
    private String showroomAddress3;

    @Column(length = 50)
    private String showroomAddress4;

    @Column(length = 50)
    private String showroomPostOffice;

    @NotBlank(message = "showroom Pin code can`t be blank")
    @Column(length = 50)
    private Integer showroomPincode;

    @Column(length = 50)
    private String showroomTehsil;

    @Column(length = 50)
    private String showroomCity;

    @Column(length = 50)
    private String showroomDistrict;

    @Column(length = 50)
    private String showroomState;

    @Column(length = 50)
    private String showroomCountry;

    //workshop address
    @NotBlank(message = "workshop Address1 can`t be blank")
    @Column(length = 50)
    private String workshopAddress1;

    @NotBlank(message = "workshop Address2 can`t be blank")
    @Column(length = 50)
    private String workshopAddress2;

    @NotBlank(message = "workshop Address3 can`t be blank")
    @Column(length = 50)
    private String workshopAddress3;

    @Column(length = 50)
    private String workshopAddress4;

    @Column(length = 50)
    private String workshopPostOffice;

    @NotBlank(message = "workshop Pin Code can`t be blank")
    @Column(length = 50)
    private Integer workshopPincode;

    @Column(length = 50)
    private String workshopTehsil;

    @Column(length = 50)
    private String workshopCity;

    @Column(length = 50)
    private String workshopDistrict;

    @Column(length = 50)
    private String workshopState;

    @Column(length = 50)
    private String workshopCountry;

    //check with kubota as the SRS tells about multiple dealer Type
    @Column(length = 50)
    private String dealerType;

    private Integer starRating;

    //dealer Contact deatils are not clear
    @NotBlank(message = "Employee Code can`t be blank")
    @Column(length = 50)
    private String employeeCode;

    @NotBlank(message = "Employee Name can`t be blank")
    @Column(length = 50)
    private String employeeName;

    @NotBlank(message = "Employee Designation can`t be blank")
    @Column(length = 50)
    private String employeeDesignation;

    @NotBlank(message = "Employee Email Id can`t be blank")
    @Column(length = 50)
    private String employeeEmailId;

    @NotBlank(message = "mobile No can`t be blank")
    @Column(length = 50)
    private String mobileNo;

   /* @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "kai_sales_representative_dealer_mapping",
            joinColumns = @JoinColumn(name = "dealer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kubota_employee_id", referencedColumnName = "id"))
    private KubotaEmployeeMaster kubotaSalesEmployee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "kai_marketing_representative_dealer_mapping",
            joinColumns = @JoinColumn(name = "dealer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kubota_employee_id", referencedColumnName = "id"))
    private KubotaEmployeeMaster kubotaMarketingEmployee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "kai_service_representative_dealer_mapping",
            joinColumns = @JoinColumn(name = "dealer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kubota_employee_id", referencedColumnName = "id"))
    private KubotaEmployeeMaster kubotaServiceEmployee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "kai_warranty_representative_dealer_mapping",
            joinColumns = @JoinColumn(name = "dealer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kubota_employee_id", referencedColumnName = "id"))
    private KubotaEmployeeMaster kubotaWarrantyEmployee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "kai_spares_parts_representative_dealer_mapping",
            joinColumns = @JoinColumn(name = "dealer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kubota_employee_id", referencedColumnName = "id"))
    private KubotaEmployeeMaster kubotaSparesPartsEmployee;*/

    //kubota department master
//  @ManyToOne(cascade = CascadeType.ALL)
//  @JoinTable(name = "department_id")
//  private DepartmentMaster departmentMaster;

    //type of dealer

    //DEALER CONTACT DETAILS

    // KAI REPRESENTATIVES ( Only for KAI Use & Not Visible to Dealer)

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate = new Date();

}
