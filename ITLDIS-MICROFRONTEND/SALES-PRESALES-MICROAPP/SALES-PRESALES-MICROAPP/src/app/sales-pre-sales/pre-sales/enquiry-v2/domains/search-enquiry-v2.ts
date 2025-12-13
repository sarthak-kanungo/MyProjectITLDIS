export interface SearchEnquiryFilter {
    page: number
    size: number
    userId: number
    enquiryNumber: string
    enquiryType: string
    model: string
    salesPerson: string
    fromDate: string
    toDate: string,
    source: string
    enquiryStatus: string
    retailConversionActivity: string
    product: string
    subModel: string;
    series: string
    variant: string
    itemNo: string
    finance: string
    autoClose: string
    subSidy: string
    exchange: string
    nextFollowUpFromDate: string
    nextFollowUpToDate: string
    tentativePurchaseFromDate: string
    tentativePurchaseToDate: string
    dealerCode?: any,
    dealerId?:number,        
    hierId?:number,
    state?:string,
    village?:string,
    showPartial?:boolean 
    pageIndexValue?:any;       
}

export interface SearchEnqiryList {
    action?: string;
    enquiryNumber: string;
    enquiryDate: string;
    salesPerson: string;
    referralPersonName?: string;
    enquiryStatus: string;
    enquiryType: string;
    followUpType: string;
    source: string;
    purposeOfPurchase: string;
    generationActivityType?: string;
    generationActivityNumber?: string;
    retailConversionActivity?: string;
    conversionActivityType?: string;
    conversionActivityNumber?: any;
    tentativePurchaseDate: string;
    nextFollowUpDate: string;
    remarks: string;
    itemNumber?: string;
    itemDescription?: string;
    variant: string;
    subModel: string;
    model: string;
    series?: string;
    product?: string;
    exchangeRequired: string;
    exchangeBrand?: string;
    exchangeModel?: string;
    estimatedExchangePrice?: string;
    prospectType: string;
    companyName?: any;
    firstName: string;
    middleName?: string;
    lastName: string;
    fatherName: string;
    mobileNumber: string;
    telephoneNumber?: any;
    whatsAppNumber: string;
    emailId?: any;
    address1: string;
    address2?: string;
    address3?: string;
    pinCode: string;
    postOffice: string;
    tehsil: string;
    district: string;
    city: string;
    state: string;
    country: string;
    language?: string;
    dob?: string;
    anniversaryDate?: string;
    gstNumber?: any;
    panNumber?: string;
    occupation: string;
    landSize: number;
    soilType?: string;
    cropGrown?: string;
    assetValue: string;
    cashLoan: string;
    financier?: any;
    financeLoggedDate?: string;
    financeStatus?: any;
    disbursedDate?: string;
    disbursedFinanceAmount: string;
    financeExchangePrice?: string;
    subsidy?: string;
    subsidyAmount?: string;
    marginAmount: string;
    currentFollowUpDate: string;
    appEnquiryFlag: boolean;
    validationDate: string;
    id: number;
    autoClose: boolean;
}

export interface EnquiryNoNameThesil {
    id:number;
    enquiryNumber: string;
    value: string;
}

export interface VillageTehsilDistrict {
    villageId:number;
    villageName: string;
    value: string;
}

export interface Product {
    product: string
}

export interface SeriesByProduct {
    series: string;
}

export interface ModelBySeries {
    model: string;
}

export interface Variants {
    variant: string;
}
export interface AutoDealerCodeSearch {
    id: number,
    code: string,
    name: string,
    displayString: string
}
export interface EnquiryStatus {
    status : string
    id: string
}