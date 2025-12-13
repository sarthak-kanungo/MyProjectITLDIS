export interface CustomerCodeAuto { }
export interface MobileNumberAuto { }
export interface SubmitSearchDto { 
    page: number;
    size: number;
    customerCode: string;
    mobileNo: string;
}
export interface CustomerSearchDto { 
    customerCode: string;
    customerName: string;
    mobileNo: string;
}
export interface CustomerMasterDto { 
    id?: number;
    custReqId?:number
    customerCode: string;
    recordType: string;
    customerType: string;
    companyName: string;
    title: string;
    editCount:string;
    firstName: string;
    middleName: string;
    lastName: string;
    fatherName: string;
    std: number;
    telephoneNumber: string;
    whatsAppNumber: string;
    language: string;
    emailId: string;
    aadharNo: string;
    dob: string;
    mobileNumber: string;
    alternateMobileNumber: string;
    anniversaryDate: string;
    gstNumber: string;
    panNumber: string;
    address1: string;
    address2: string;
    address3: string;
    pinCode: string;
    pinId: number;
    postOffice: string;
    city: string;
    tehsil: string;
    district: string;
    state: string;
    country: string;
    occupation: string;
    landSize: string;
    chassisNo: string;

    prospectSoilTypes: ProspectSoilType[];
    prospectCropNames: ProspectCropGrown[];
    prospectMachineryDetails: ProspectMachineryDetail[];
    vehicleDetails: Sales[];
    
}

export interface ProspectSoilType { 
    id?: number;
    soilName: string;
}
export interface ProspectCropGrown { 
    id?: number;
    cropName: string;
}
export interface ProspectMachineryDetail { 
    id: number;
    brand: string;
    model: string;
    yearOfPurchase: string;
}
export interface Sales { 
    id: number;
    chassisno: string;
    registrationno: string;
    modelcode: string;
    modeldesc: string;
}

export interface SubmitCreateDto { }
export interface CreateCustomerCodeAuto { }
export interface CustomerTypeDropdown {
    prospectType: string;
}
export interface CustomerGroupAuto { }
export interface TitleDropdown { 
    title: string,
    id: number
}
export interface QualificationDropdown { 
    occupation: string;
}
export interface PreferrdeLanguageDropdown {
    lookuptext:string
}
export interface AddressTypeDropdown { }
export interface PinCodeAuto { }
export interface LocalityDropdown { }

export interface SoilTypeDropdown { 
    soilType: string;
    id?: number
}

export interface CropsGrownDropdown { 
    cropGrown: string;
    id?: number
}

export interface ManufacturerDropdown {
    brand: string;
    id?: number
}
export interface ModelDropDown { 
    model: string
}
export interface YearOfPurchaseAuto { }
export interface SerialNoAuto { }

export interface AutoCompPinCode {
    value: string
    pinCode: string
    pinID: number
    cityId: number
}
export interface PincodeDetail {
    tehsil: string
    city: string
    pinCode: number
    pinID:number
    postOffice: string
    state: string,
    country: string
}
export interface DealerInformation {
    state?: string
    country?: string
    district?: string
    districtId?: number
}