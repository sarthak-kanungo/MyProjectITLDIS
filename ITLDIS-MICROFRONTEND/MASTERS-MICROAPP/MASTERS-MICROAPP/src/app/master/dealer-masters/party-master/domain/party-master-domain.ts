
export interface CategoryList {
id:number,
CategoryCode:string
displayValue:string
}

export interface PartyCode {
partyCode:string
id:any;
}
export interface PartyName {
  partyName:string
  id:any;
  }
export interface Title {

}
export interface SearchPartyMaster {
  partyCode: string;
  partyName: string;
  page: number;
  size: number;
}

export interface SearchPartyResponse {
  id: number;
  activeStatus: string;
  creditLimit: string | number;
  partyCode: string;
  dealerFirmType: string;
  partyName: string;
  partyType: string;
  emailId: string;
  gstNumber: string;
  mobileNumber: string;
  panNumber: string;
  state: string;
  subsidyDealer: string;
  action?:any;
  branchId?:any;
}
export interface SubmitDto {
address1: string
address2: string
address3: string
city: string
country: string
email: string
locality: string
pinCode: any
state: string
std: string
tehsil: string
telephoneNo: string
adharCardNumber: string
category: string
designation: string
firstName: string
gstNumber: number
lastName: string
middleName: string
mobileNumber: string
panNumber: number
partyCode?: string
partyLocation: string
partyName: string
title: string
postOffice:string
pinid:any;
partyCategoryId:any;
contactName:any;
id?:any;
branchId:any;
}
export interface DealerInformation {
  state?: string
  country?: string
  district?: string
  districtId?: number
}
export interface PinCodeAuto { }
export interface LocalityList { }

export interface AutoCompPinCode {
  value: string
  pinCode: string
  pinID: number
  cityId: number
}

export interface PostOffice {
  postOffice: string
}
export interface PincodeDetail {
  tehsil: string
  city: string
  pinCode: number
  pinID:number
  postOffice: string
}
  export interface AutocomplatePopulateDatabyPinCode {
    city: string;
    state: string;
    district: string;
    tehsil: string;
    country: string;
  }

  export interface CountryDetails {
    id: number,
    country: string
}

export interface StateDetails {
    id: number,
    state: string
}
export interface DistrictDetails {
    id: number,
    district: string
}
export interface TehsilDetails {
    id: number,
    tehsil: string
}
export interface CityDetails {
    id: number,
    city: string
}
export interface PinCodeDetails {
    pinID: number,
    pinCode: number
}

export interface PostOfficeDetails {
    id: number,
    postOffice: string
}

export interface LocalityDetails {
  id: number,
  locality: string
}
export interface BranchCodeDetails {
  id: number,
  code: string,
  name:string,
  displayString:string
}