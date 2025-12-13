package com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({"edit","id","employeeCode","title","employeeName","emailId","mobileNumber","alternateMobileNumber",
	"departmentName","designation","licenceType","drivingLicenceNumber","drivingLicenceExpiryDate","joiningDate",
	"latestSalary","leavingDate","pfNumber","panNumber","esiNumber","bankAccountNumber","bankName","bankBranch",
	"address1","address2","address3","pinCode","locality","tehsil","district","city","state","country","activeStatus"})

public interface DealerEmployeeSearchResponse {

    Long getId();

    String getEmployeeCode();

    String getTitle();

    Character getActiveStatus();

    String getEmployeeName();

//    String getMiddleName();
//
//    String getlastName();

    String getEmailId();

    String getMobileNumber();

    String getAlternateMobileNumber();

    String getDepartmentName();

    String getDesignation();

    String getLicenceType();

    String getDrivingLicenceNumber();

    Date getDrivingLicenceExpiryDate();

    Date getJoiningDate();

    String getLatestSalary();

    Date getLeavingDate();

    String getPfNumber();

    String getPanNumber();

    String getEsiNumber();

    String getBankAccountNumber();

    String getBankName();

    String getBankBranch();

    String geAddress1();

    String geAddress2();

    String geAddress3();

    String getPinCode();

    String getLocality();

    String getTehsil();

    String getDistrict();

    String getCity();

    String getState();

    String getCountry();
    
    String getEdit();
    
    @JsonIgnore
   	public Long getCount();
}
