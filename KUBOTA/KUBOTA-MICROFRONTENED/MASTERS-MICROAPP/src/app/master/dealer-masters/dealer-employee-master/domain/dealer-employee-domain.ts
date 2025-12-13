export interface EmployeeCode {
    employeeCode: string;
    id: number;
}
export interface TitleDropdown { }
export interface StatusDropdown { }
export interface DivisionDropdown { }

export interface DepartmentDropdown {
    department:string
    id:number
 }
export interface DesidnationDropdown {
    designation:string
    id:number
 }
export interface LicenceTypeDropdown { }
// export interface ReportingEmployeeCodeAuto { 
//     id:number
//     employeeName:string
// }

export interface ReportingEmployeeCodeAuto { 
    displayValue:string
    reportingEmpCode:string
    reportingEmpID:any
    reportingEmpName:string
}

export interface DealerMasterDropdown {
 id: number;
 value: string;
}
export interface QualificationsDropdown {
    id: number;
    highsetQualification: string;
   }
   export interface MaritalsDropdown {
       id: number;
       maritalStatus: string;
   }
   export interface BloodsDropdown {
       id: number;
       bloodGroup: string;
   }
   export interface GenderDropdown {
       M: number;
       F: string;
   }
   export interface AutoEmployeeCode {
       id: number
       employeeCode?: string,
   }
   export interface AutoEmployeeName{
    id:number,
    employeeName:string
   }

   export interface AutoEmpMobile {
    id: number
    mobilenumber?: string,
}
   export interface AutoDealerDetails {
    id: number
    code?: string,
    displayString:string,
    name:string
    dealerShip:string
    }

export interface EmergencyContactNoAuto { }

export interface PinCodeAuto { }
export interface PostOfficeDropdown { }

export interface ReleationshipDropdown { }
export interface EmployeeCodeAuto { }
export interface MobileNoAuto { }

export interface CountryDetails {
    id: any,
    country: string
}

export interface StateDetails {
    id: any,
    state: string
}
export interface DistrictDetails {
    id: any,
    district: string
}
export interface TehsilDetails {
    id: any,
    tehsil: string
}
export interface CityDetails {
    id: any,
    city: string
}
export interface PinCodeDetails {
    pinID: any,
    pinCode: number
}

export interface PostOfficeDetails {
    id: any,
    postOffice: string
}


export interface SubmitDto {
    id:number
    dealerId:number
    employeeCode:string
    title:string
    firstName:string
    middleName:string
    lastName:string
    status:string
    activeStatus:string
    emailId:string
    mobileNo:number
    alternateMobileNo:number
    division:string
    departmentId:string
    designationId:string
    licenceType:string
    drivingLicenceNo:string
    expiryDate:string
    drivingLicenceExpiryDate:string
    reportingEmployeeId:number
    reportingEmployeeName:string

    birthDate:string
    anniversaryDate:string
    highestQualification:string
    maritalStatus:string
    bloodGroup:string
    sex:string
    emergencyContactName:string
    emergencyContactNo:number
    preFromDate:string,
    preToDate:string,
    totalExperience:number
    joiningDate:string
    latestSalary:number
    leavingDate:string
    pfNo:number
    panNo:number
    aadharNo:string
    esiNo:number
    bankAccountNo:string
    bankName:string
    bankBranch:string
    ifsCode:string
    authorizedDiscount:any

    address1:string
    address2:string
    address3:string
    pinCode:number
    locality:string
    tehsil:string
    district:string
    city:string
    state:string
    country:string

    isSelected:any
    name:string
    relationship:string

    companyName:string
    fromDate:string
    toDate:string
    designation:string
    role:string
    message:string

 }
export interface SearchDto { }
