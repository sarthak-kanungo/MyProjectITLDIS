export interface AutoCompleteMobileNo {
  value: string;
  mobileNumber: string;
  id: number;
  prospectCode?: string
  customerCode?: string
}

export interface AutoRetailer {
  id: number,
  value: string
  code: string,
}

export interface AutoMachine {
  id: number,
  value: string
  code: string,
}

export interface CustomerDetails {
  mobileNumber: string;
  address2?: any;
  state: string;
  stateId? : number
  tehsil: string;
  tehsilId? : number
  address1: string;
  prospectMasterId: number;
  customerName: string;
  district: string;
  districtId? : number
  country: string;
  countryId? : number
  city: string;
  cityId? : number
  pinCode? : number
  pinCodeId? : number
  postOffice?: string;
  customerMasterId? : number
}

export interface RetailerOrMachinePatchJson {
  contactNumber: string;
  country: string;
  customerAddress1: string;
  customerAddress2: string;
  district: string;
  pinCode: string;
  postOffice: string;
  retailerName: string;
  state: string;
  tehsil: string;
  village: string;
  id: number;
pinCodeId:number;
}

export interface CustomerType {
  id: number;
  customerType: string
}

export interface Country {
  id: number;
  country: string;
}

export interface AutoState {
  state: string;
  id: number;
}

export interface AutoDistrict{
  district: string;
  id: number;
}

export interface AutoTehsil {
  tehsil: string;
  id: number;
}

export interface AutoCity {
  city: string;
  id: number;
}

export interface AutoPinCode {
  post_office: string;
  pinCode: number;
  id: number;
}

export interface AutoPostOffice {
  postOffice: string;
  id: number;
}

export interface AutoItemNo {
  itemNo: string;
  id: number;
}

export interface DetailsByItemNo{
  sgstPercent?: number;
  unitPrice: number;
  cgstPercent?: number;
  hsnCode: string;
  id: number;
  itemNo: string;
  itemDescription: string;
  igstPercent? : number
}

export interface SaveAndSubmitQuotation {
  customerMaster: CustomerMaster;
  customerType: string;
  discountRate: number;
  draftFlag: boolean;
  quotationPartDetail: QuotationPartDetail[];
  totalBasicValue: number;
  totalDiscountValue: number;
  totalQuotationAmount: number;
  totalTaxValue: number;
  contactNumber: string;
  country: string;
  customerAddress1: string;
  customerAddress2: string;
  customerName: string;
  district: string;
  prospectMaster: CustomerMaster;
  state: string;
  village: string;
  quotationNumber?: string;
  mobileNumber?: string;
  quotationDate?: string;
  tehsil?: string;
  Tehsil_id?: number
  id?: number;
  stateId? : number
  State_id? : number
  tehsilId? : number
  districtId? : number
  District_id?: number
  countryId? : number
  cityId? : number
  City_id?: number
  pinCodeId? : number
  pinCode? : number
  pinId: number,
  partyMaster: CustomerMaster,
  postOffice? : string
  city?: string
  retailer?: string;
}

export interface QuotationPartDetail {
  amount: number;
  cgstAmount: number;
  cgstPercent: number;
  discountPercent: number;
  discountAmount: number;
  gstAmount: number;
  hsnCode: string;
  igstAmount: number;
  igstPercent: number;
  itemDescription: string;
  itemNumber: string;
  itemNo?: string;
  netAmount: number;
  quantity: number;
  sgstAmount: number;
  sgstPercent: number;
  subTotal: number;
  total: string;
  totalInvoiceAmount: number;
  unitPrice: number;
  sparePartMaster: SparePartMaster;
  sparePartMasterId?: number;
  id?: number;
  isValidItem:string
}

export interface SparePartMaster {
  id: number;
  itemNo: string;
}

export interface CustomerMaster {
  id: number;
}

export interface FilterQuotationSearch {
  customerName? : string,
  customerType? : string,
  page: number,
  quotationFromDate? : string,
  quotationId? : number,
  quotationToDate? : string,
  size: number
  quotationNumber?: string
}

export interface QuotationNumber {
  quotationNumber: string;
  id: number;
}

export interface AutoCustomerName {
  customerName: string;
  id: number;
}

export interface ViewPartQuotation {
  headerResponse: SaveAndSubmitQuotation;
  partDetails: QuotationPartDetail[];
}

