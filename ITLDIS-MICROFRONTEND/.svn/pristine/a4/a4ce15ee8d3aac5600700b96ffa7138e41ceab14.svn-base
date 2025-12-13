
declare module "ProspectDetails" {
  import { CurrentMachineObj } from "CurrentMachineDetails";
  import { ProspectBackgroundObj, EnquiryCropGrow, EnquirySoilType } from "ProspectBackground";

  export interface DropDownprospectType {
    prospectType: string;
  }
  export interface AutoComplateMobileNo {
    mobileNumber: string
  }
  export interface AutoProspectCode {
    prospectCode: string;
  }

  export interface ProspectDetailsObj {
    prospectCode: string,
    prospectType: string,
    companyName: string,
    customerCode: string;
    customerType: string;
    oldCustomerCode: string;
    firstName: string,
    middleName: string,
    lastName: string,
    fatherName: string,
    mobileNumber: string,
    whatsAppNumber: string,
    alternateMobileNumber: string,
    std: number,
    telephoneNumber: string,
    emailId: string,
    address1: string,
    address2: string,
    address3: string,
    pinCode: number,
    postOffice?: any;
    city: string,
    tehsil?: any,
    district?: any,
    state: string,
    country: string,
    language?: any;
    dob: string,
    anniversaryDate?: any,
    gstNumber?: any;
    panNumber?: any;
    prospectCurrentMachineryDetail: CurrentMachineObj[];
    prospectCropNames: EnquiryCropGrow[];
    prospectSoilTypes: EnquirySoilType[];
    occupation: string;
    landSize: number;
    id: number
  }

  export interface SaveProspectDetailsObj {
    prospectCode: string,
    prospectType: string,
    companyName: string,
    firstName: string,
    middleName: string,
    lastName: string,
    fatherName: string,
    mobileNumber: string,
    whatsAppNumber: string,
    alternateMobileNumber: string,
    std: number,
    telephoneNumber: string,
    emailId: string,
    address1: string,
    address2: string,
    address3: string,
    pinCode: number,
    postOffice?: any;
    city: string,
    tehsil?: any,
    district?: any,
    state: string,
    country: string,
    language?: any;
    dob: string,
    anniversaryDate?: any,
    gstNumber?: any;
    panNumber?: any;
  }

  export interface PinCode {
    pinCode: number;
    value : number;
    cityId : number
  }

  export interface AutocomplatePopulateDatabyPinCode {
    city: string;
    state: string;
    district: string;
    tehsil: string;
    country: string;
  }
  export interface AutoComplatePostOffice {
    postOffice: string
  }


}