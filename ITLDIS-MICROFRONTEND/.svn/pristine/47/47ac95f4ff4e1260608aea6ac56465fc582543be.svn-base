export interface SaveSalesOrder {
    id?: number;
    contactNumber: string;
    country: string;
    customerAddress1: string;
    customerAddress2: string;
    customerOrderDate: string;
    customerName: string;
    customerType: string;
    dicountType: string;
    discountRate: number;
    district: string;
    draftFlag: boolean;
    customerMaster: SpareQuotation;
    prospectMasterId: SpareQuotation;
    spareQuotation: SpareQuotation;
    spareSalesOrderPartDetailList: SpareSalesOrderPartDetailList[];
    state: string;
    tehsil: string;
    totalBasicValue: number;
    totalDiscountValue: number;
    totalSalesOrderAmount: number;
    totalTaxValue: number;
    village: string;
    pinCode: string;
    pinId: string;
    postOffice: string;
    partyMaster: SpareQuotation,
    coDealer: SpareQuotation,
}
export interface PartDetails {
    id: number,
    unitPrice: number
    availableQuantity: number;
    availableStock?:number;
    binLocation: string;
    binId: number;
    storeId: number;
    store: string;
    uniqueKey: string;
    issueQuantity?: number;
}
export interface SpareSalesOrderPartDetailList {
    binLocation: string;
    cgstAmount: number;
    cgstPercent: number;
    discountAmount: number;
    discountPercent: number;
    gstAmount: number;
    hsnCode: string;
    igstAmount: number;
    igstpercent: number;
    issueQuantity: number;
    itemDescription: string;
    itemNo: string;
    netAmount: number;
    quantity: number;
    sgstAmount: number;
    sgstPercent: number;
    sparePartMaster: SparePartMaster;
    store: string;
    subTotal: number;
    totalInvoiceAmount: number;
    unitPrice: number;
    binLocationMaster: BinLocationMaster;
}
export interface BinLocationMaster {
    id: number;
}
export interface SparePartMaster {
    id: number;
    itemNo: string;
}

export interface SpareQuotation {
    id: number;
}
export interface ItemNumberAuto {
    itemNo: string
    id: number,
    value: string
}

export interface CustomerOrderNumber {
    saleOrderNumber: string,
    id: string
}
export interface CustomerName {
    customerName: string
}
export interface AutoQuotationNo {
    quotationNumber: string,
    id: number
}
export interface AutoCompleteCustomerNMobileNo {
    id: number,
    mobileNumber: number,
    prospectCode: string,
    value: string
    customerCode: string,
}
export interface AutoCompleteDealerCode {
    id: number,
    value: string
    code: string,
}
export interface AutoCompleteRetailer {
    id: number,
    value: string
    code: string,
}
export interface AutoCompleteMachine {
    id: number,
    value: string
    code: string,
}
export interface CustomerDetails {
    customerName: string,
    id: number,
    mobileNumber: string,
    address2: string,
    state: string,
    tehsil: string,
    address1: string,
    district: string,
    country: string,
    city: string
    pinCode: string
    postOffice: string
    stateId?: number
    tehsilId?: number
    districtId?: number
    countryId?: number
    cityId?: number
    pinCodeId?: number
    prospectMasterId?: number
    customerMasterId?: number
}
export interface CustomerType {
    id: number;
    customerType: string
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
    id: number,
    pinCode: string
}

export interface PostOfficeDetails {
    id: number,
    postOffice: string
}

export interface QuotationPatchJson {
    headerResponse: HeaderResponse;
    partDetailsForSalesOrder: PartDetail[];
}
export interface DealerCodePatchJson {
    contactNumber: string;
    country: string;
    customerAddress1: string;
    customerAddress2: string;
    district: string;
    pinCode: string;
    pinId:number;
    postOffice: string;
    dealerName: string;
    state: string;
    tehsil: string;
    village: string;
    id: number
}
export interface RetailerPatchJson {
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
    pinId:number;
}
export interface MachinePatchJson {
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
    id: number
    pinId:number;
}
export interface PartDetail {
    cgstPercent: number;
    sparePartMasterId: number;
    itemNumber: string;
    gstAmount: number;
    quantity: number;
    sgstAmount: number;
    discountPercent: number;
    igstAmount: number;
    cgstAmount: number;
    netAmount: number;
    hsnCode: string;
    totalGstAmount?: any;
    id: number;
    discountAmount: number;
    subTotal: number;
    sgstPercent: number;
    amount: number;
    igstPercentl: number;
    unitPrice: number;
    itemDescription: string;
    binId: number;
    isValidItem?:string;
    total:number
}

export interface HeaderResponse {
    country?: any;
    discountRate: number;
    mobileNumber?: any;
    quotationNumber: string;
    tehsil?: any;
    customerName?: any;
    quotationDate: string;
    draftFlag: boolean;
    customerType: string;
    district?: any;
    totalDiscountValue?: any;
    totalQuotationAmount: number;
    totalSalesOrderAmount: number;
    customerAddress1?: any;
    state?: any;
    customerAddress2?: any;
    discountType: string;
    totalBasicValue: number;
    totalTaxValue: number;
    salesOrderStatus?:string;
    showInvoice?:boolean;
    city?: any;
    pinCode?: any;
    pinId?: any;
    postOffice?: any;
    id?: number;
    stateId?: number
    tehsilId?: number
    districtId?: number
    countryId?: number
    cityId?: number
    pinCodeId?: number
    prospectMasterId?: number
    customerMasterId?: number
    salesOrderDate: string;
    contactNumber: string;
    saleOrderNumber: string;
    retailer: string;
    dealerCode: string;
    spareQuotationId?: number
}

export interface SoPatchJson {
    headerResponse: HeaderResponse;
    partDetailsForSalesOrder: PartDetail[];
}

//   interface PartDetail {
//     issueQuatitiy?: any;
//     binLocation?: any;
//     cgstPercent?: any;
//     igstAmount: number;
//     hsnCode: number;
//     totalInvoiceAmount: number;
//     discountAmount: number;
//     itemNo: string;
//     store?: any;
//     sparePartMasterId: number;
//     sgstAmount?: any;
//     cgstAmount?: any;
//     subTotal: number;
//     itemDescription: string;
//     netAmount: number;
//     gstAmount: number;
//     unitPrice: number;
//     quantity: number;
//     id: number;
//     amount: number;
//     sgstPercent?: any;
//   }

//   interface HeaderResponse {
//     discountRate?: any;
//     quotationNumber?: any;
//     salesOrderDate: string;
//     state: string;
//     customerType: string;
//     contactNumber: string;
//     district: string;
//     country: string;
//     edit: string;
//     customerName: string;
//     customerAddress1: string;
//     tehsil: string;
//     pinCode?: any;
//     saleOrderNumber: string;
//     discountType?: any;
//     customerAddress2?: any;
//     id: number;
//     postOffice?: any;
//   }
export interface SoSearch {

    customerName: string;
    customerType: string;
    orderFromDate: string;
    orderStatus: string;
    orderToDate: string;

    salesOrderId?: number;

    page: number;
    size: number;
}



export interface ItemDetails {
    amount: number;
    binQuantity?: any;
    cgstAmount: number;
    cgstPercent?: any;
    discountAmount: string;
    discountPercent: string;
    hsnCode: number;
    igstAmount: string;
    igstPercent: number;
    isSelected: boolean;
    issueQuantity?: any;
    itemDescription: string;
    itemNo: string;
    netAmount: string;
    quantity: string;
    sgstAmount: number;
    sgstPercent?: any;
    store?: any;
    unitPrice: number;
}