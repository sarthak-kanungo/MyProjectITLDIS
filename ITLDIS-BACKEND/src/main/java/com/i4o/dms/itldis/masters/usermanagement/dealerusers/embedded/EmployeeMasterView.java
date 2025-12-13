package com.i4o.dms.itldis.masters.usermanagement.dealerusers.embedded;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeMasterView {
	
    private Long dealerId;
    private Long employeeCode;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String status;
    private String emailId;
    private Long mobileNo;
    private Long alternateMobileNo;
    private String division;
    private String departmentId;
    private String designationId;
    private String licenceType;
    private String drivingLicenceNo;
    private String expiryDate;
    private Long reportingEmployeeId;
    private String reportingEmployeeName;

    private Date birthDate;
    private Date anniversaryDate;
    private String highestQualification;
    private String maritalStatus;
    private String bloodGroup;
    private String sex;
    private String emergencyContactName;
    private String emergencyContactNo;

    private Date joiningDate;
    private Long latestSalary;
    private String leavingDate;
    private Long pfNo;
    private Long panNo;
    private Long esiNo;
    private Long bankAccountNo;
    private String bankName;
    private String bankBranch;
    private Long authorizedDiscount;

    private String address1;
    private String address2;
    private String address3;
    private Long pinCode;
    private String locality;
    private String tehsil;
    private String district;
    private String city;
    private String state;
    private String country;

    private String isSelected;
    private String name;
    private String relationship;

    private String companyName;
    private Date fromDate;
    private Date toDate;
    private String designation;
    private String role;


}
