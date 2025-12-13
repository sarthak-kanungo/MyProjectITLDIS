import { UploadableFile } from "kubota-file-upload";

export interface FollowUpType {
    followUpType: string;
    id?: number
}

export interface EnquirySource {
    source: string
    id?: number
}

export interface RetailConversionActivityType {
    retailConversionActivity: string;
    id?: number
}

export interface PurposeOfPurchase {
    purposeOfPurchase: string;
    id?: number
}

export interface ActivationType {
    conversionActivityType: string;
    id?: number
}

export interface GenerationActivationType {
    generationActivityType: string;
    id?: number
}

export interface SalePerson {
    id: number,
    salesPerson: string,
    lastName?: string
}

export interface ExchangeBrand {
    exchangeBrand: string;
    id?: number
}

export interface ProspectType {
    prospectType: string;
}

export interface Occupation {
    occupation: string;
    id?: number
}

export interface SoilType {
    soilType: string;
    id?: number
}

export interface MajorCropGrown {
    cropGrown: string;
    id?: number
}

export interface Brand {
    brand: string;
    id?: number
}

export interface CashLoan {
    cashLoan: string;
    id?: number
}
export interface FinalStatus {
    financeStatus: string;
    id?: number
}

export interface Financier {
    financierName: string;
    financierCode: string;
    financierLocation: string;
    value: string;
    id?: number
}

export interface EnquiryType {
    enquiryType: string;
}

export interface ActivityNumber {
    activityNumber: string;
    activityProposalId: number;
}

export interface ItemNumber {
    itemNo: string;
    value?: string;
    id?: number;
    itemNumber?: string;
}

export interface PopulateByItemNo {
    model?: string;
    subModel?: string;
    variant?: string;
    series?: string;
    product?: string;
    itemDescription?: string;
}

export interface Model {
    model: string;
}

export interface AutoCompSubModel {
    subModel: string;
}

export interface AutoCompVariant {
    variant: string;
}

export interface MobileNo {
    mobileNumber: string,
    id: number
    oldCustomerCode: string
    prospectCode: string
    value:string
}


export interface AutoCompPinCode {
    value: string
    pinCode: string
    pinID: number
    cityId: number
}

export interface PostOffice {
    postOffice: string
}

export interface Title {
    title: string,
    id: number
}

export interface GenerateEnquiryNumber {
    enquiryNumber: string
}

export interface SaveEnquiry {
    recordType?:string
    aadharNo?: string;
    address1?: string;
    address2?: string;
    address3?: string;
    alternateMobileNo?: string;
    anniversaryDate?: string;
    assetValue?: number;
    cashLoan?: string;
    city?: string;
    companyName?: string;
    conversionActivityNumber?: ConversionActivityNumber;
    conversionActivityType?: string;
    country?: string;
    createdBy: number;
    salesPerson?: CreatedBy;
    createdDate?: string;
    customerCode?: string;
    customerType?: string;
    oldCustomerCode?: string;
    prospectMaster?: ProspectMaster;
    customerMaster?: CustomerMaster;
    disbursedDate?: string;
    disbursedFinanceAmount?: string;
    district?: string;
    dob?: string;
    emailId?: string;
    enquiryCropGrows?: EnquiryCropGrow[];
    enquiryDate?: string;
    enquiryMachineryDetails?: CurrentMachine[];
    enquiryNumber?: string;
    enquirySoilTypes?: EnquirySoilType[];
    prospectSoilTypes?: EnquirySoilType[];
    prospectCropNames?: EnquiryCropGrow[];
    enquiryStatus?: string;
    enquiryType?: string;
    estimatedExchangePrice?: number;
    estimatedFinanceAmount?: number;
    // exchangeAmount: number;
    exchangeBrand?: string;
    exchangeModel?: string;
    exchangeRequired?: string;

    exchangeModelYear? : number;
    exchangeReceived?: string
    
    fatherName?: string;
    finalExchangePrice?: number;
    financeLoggedInDate?: string;
    financeStatus?: string;
    financier?: string;
    financierId?: number;
    firstName?: string;
    followUpType?: string;
    generationActivityNumber?: ConversionActivityNumber;
    generationActivityType?: string;
    gstNo?: string;
    id?: number;
    itemDescription?: string;
    itemNo?: string;
    language?: string;
    lastModifiedBy?: string;
    lastModifiedDate?: string;
    lastName?: string;
    middleName?: string;
    mobileNumber?: string;
    model?: string;
    nextFollowUpDate?: string;
    pancardNo?: string;
    pinCode?: number;
    pinId?:number;
    postOffice?: string;
    product?: string;
    prospectCode?: string;
    prospectType?: string;
    purposeOfPurchase?: string;
    referralPersonName?: string;
    remarks?: string;
    retailConversionActivity?: string;
    validationDate?: string;
    variant?: string;
    whatsAppNo?: string;
    series?: string;
    source?: string;
    state?: string;
    std?: number;
    subModel?: string;
    subsidy?: string;
    subsidyAmount?: number;
    tehsil?: string;
    telephoneNo?: string;
    tentativePurchaseDate?: string;
    title?: string;
    occupation?: string,
    landSize?: number,
    totalReceived?:number,
    multipartFileList: UploadableFile[],
    appEnquiryFlag?:boolean
}

export interface ViewEnquiry {
    id: number;
    recordType?:string;
    enquiryNumber: string;
    enquiryStatus: string;
    enquiryDate: string;
    salesPerson: SalePerson;
    referralPersonName: string;
    validationDate: string;
    enquiryType: string;
    followUpType: string;
    source: string;
    purposeOfPurchase: string;
    generationActivityType: string;
    generationActivityNumber: ActivityNumber;
    retailConversionActivity: string;
    conversionActivityType: string;
    conversionActivityNumber: ActivityNumber;
    tentativePurchaseDate: string;
    nextFollowUpDate: string;
    remarks: string;
    itemNo: string;
    itemDescription: string;
    variant: string;
    subModel: string;
    model: string;
    series: string;
    product: string;
    exchangeRequired: string;
    // exchangeAmount: number;
    exchangeBrand: string;
    exchangeModel: string;
    estimatedExchangePrice: number;
    exchangeModelYear : number;
    exchangeReceived: string
    //prospectCode: string;
    //prospectType: string;
    companyName: string;
    title: string;
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
    pinCode: number;
    postOffice: string;
    city: string;
    tehsil: string;
    district: string;
    state: string;
    country: string;
    enquirySoilTypes: SoilType[];
    enquiryCropGrows: MajorCropGrown[];
    enquiryMachineryDetails: CurrentMachine[];
    prospectMaster: ProspectMaster;
    customerMaster: CustomerMaster;
    transferTo?: any;
    assetValue: number;
    cashLoan: string;
    financier: string;
    financierId?: number;
    marginAmount: number;
    financeLoggedInDate: string;
    estimatedFinanceAmount: number;
    financeStatus: string;
    disbursedDate: string;
    disbursedFinanceAmount: string;
    finalExchangePrice: number;
    subsidy: string;
    subsidyAmount: number;
    landSize: number,
    occupation: string
    totalReceived?:number;
    subsidyReceived:boolean;
    countUpdate: number;
    mobileCountUpdate: number;
}

export interface CurrentMachine {
    brand: string;
    id: number;
    model: string;
    yearOfPurchase: string
    iSelected?: boolean
}

export interface EnquirySoilType {
    id: number;
    soilName: string;
}

export interface EnquiryCropGrow {
    cropName: string;
    id: number;
}

export interface CreatedBy {
    id: number
}

export interface ProspectMaster {
    id?: number;
    prospectCode?: string;
}

export interface ConversionActivityNumber {
    activityProposalId: number;
}


export interface CustomerMaster {
    id?: number;
    customerCode?: string;
    oldCustomerCode?: string;
    recordType?:string;
    customerType?:string;
}

export interface FollowupHistory {
    remarks: string;
    followedBy: string;
    tentativePurchaseDate: string;
    enquiryType: string;
    nextFollowUpDate: string;
    updationDate: string;
    followUpType?: string;
    followUpDate: string;
}

export interface DealerInformation {
    state?: string
    country?: string
    district?: string
    districtId?: number
}

export interface AutoTehsil {
    value: string
    tehsil: string
    id: number
    pcmId: number
    pinCodeId: number
}
export interface PincodeDetail {
    tehsil: string
    city: string
    pinCode: number
    pinID:number
    postOffice: string
}
