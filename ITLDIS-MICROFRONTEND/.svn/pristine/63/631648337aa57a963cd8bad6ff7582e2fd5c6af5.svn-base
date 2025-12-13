declare module "EnquiryCreation" {
  import { ProspectDetailsObj } from "ProspectDetails";
  import { EnquiryCropGrow, EnquirySoilType, DropDownSoilType, DropDownMajorCropGrown } from "ProspectBackground";
  import { CurrentMachineObj } from "CurrentMachineDetails";
  export interface EnquiryCode {
    enquiryCode: string;
  }
  export interface EnquiryNoNameThesilDomain {
    enquiryNumber: string;
    value: string;
  }

  export interface AutoPopulatebyEnqCode {
    id: number;
    enquiryNumber: string;
    enquiryStatus: string;
    enquiryDate?: any;
    salesPerson: string;
    referralPersonName: string;
    validationDate: string;
    enquiryType: string;
    followUpType: string;
    source: string;
    purposeOfPurchase: string;
    generationActivityType: string;
    generationActivityNumber: number;
    retailConversionActivity: string;
    conversionActivityType: string;
    conversionActivityNumber: string;
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
    exchangeBrand: string;
    exchangeModel: string;
    estimatedExchangePrice: number;
    prospectMaster: ProspectDetailsObj;
    // customerMaster?: ProspectDetailsObj;
    assetValue: number;
    cashLoan: string;
    financier: string;
    financeLoggedInDate: string;
    estimatedFinanceAmount: number;
    financeStatus: string;
    disbursedDate: string;
    disbursedFinanceAmount: string;
    finalExchangePrice: number;
    subsidy: string;
    subsidyAmount: number;
    marginAmount: number;
  }

  export interface DropDownFollowupType {
    followUpType: string;
  }

  export interface DropDownSource {
    sourceName: string
  }

  export interface UpdationDate {
    updationDate: string
  }

  export interface DropDownPurposeOfPurchase {
    purposeOfPurchase: string;
  }
  export interface DropDownGenerationActivationType {
    generationActivityType: string;
  }

  export interface DropDownConversationActivationType {
    conversionActivityType: string;
  }


  export interface DropDownRetailConversionActivityType {
    retailConversionActiviy: string;
  }

  export interface EnquiryDomain {
    conversionActivityNo: string;
    conversionActivitytype: string;
    createdBy: CreatedBy;
    itemDescription: string;
    enquiryNumber: string;
    enquiryStatus: string;
    enquiryType: string;
    estimatedExchangePrice: number;
    exchangeModel: string;
    exchangebrand: string;
    exchangeRequired: string;
    followUpType: string;
    generationActivityNo: number;
    generationActivityType: string;
    itemNo: string;
    model: string;
    product: string;
    prospectMaster: ProspectDetailsObj;
    purposeOfPurchase: string;
    referralPersonName: string;
    remarks: string;
    retailConversionActivity: string;
    salesPerson: CreatedBy;
    series: string;
    source: string;
    subModel: string;
    variant: string;
  }

 export interface EnquiryWithProspectMasterDomain {
    aadharNo: string;
    address1: string;
    address2: string;
    address3: string;
    alternateMobileNo: string;
    anniversaryDate: string;
    assetValue: number;
    cashLoan: string;
    city: string;
    companyName: string;
    conversionActivityNumber: ConversionActivityNumber;
    conversionActivityType: string;
    country: string;
    createdBy: CreatedBy;
    //salesPerson: CreatedBy;
    createdDate: string;
    prospectMaster: ProspectMaster;
    disbursedDate: string;
    disbursedFinanceAmount: string;
    district: string;
    dob: string;
    emailId: string;
    enquiryCropGrows: EnquiryCropGrow[];
    enquiryDate: string;
    enquiryMachineryDetails: CurrentMachineObj[];
    enquiryNumber: string;
    enquirySoilTypes: EnquirySoilType[];
    enquiryStatus: string;
    enquiryType: string;
    estimatedExchangePrice: number;
    estimatedFinanceAmount: number;
   // exchangeAmount: number;
    exchangeBrand: string;
    exchangeModel: string;
    exchangeRequired: string;
    fatherName: string;
    finalExchangePrice: number;
    financeLoggedInDate: string;
    financeStatus: string;
    financier: string;
    firstName: string;
    followUpType: string;
    generationActivityNumber: ConversionActivityNumber;
    generationActivityType: string;
    gstNo: string;
    id : number;
    itemDescription: string;
    itemNo: string;
    language: string;
    lastModifiedBy: string;
    lastModifiedDate: string;
    lastName: string;
    middleName: string;
    mobileNumber: string;
    model: string;
    nextFollowUpDate: string;
    pancardNo: string;
    pinCode: number;
    postOffice: string;
    product: string;
    prospectCode: string;
    purposeOfPurchase: string;
    referralPersonName: string;
    remarks: string;
    retailConversionActivity: string;
    validationDate: string;
    variant: string;
    whatsAppNo: string;
    series: string;
    source: string;
    state: string;
    std: number;
    subModel: string;
    subsidy: string;
    subsidyAmount: number;
    tehsil: string;
    telephoneNo: string;
    tentativePurchaseDate: string;
    title: string;
    occupation : string,
    landSize : number,
  }

  export interface EnquiryWithCustomerMasterDomain {
    aadharNo: string;
    address1: string;
    address2: string;
    address3: string;
    alternateMobileNo: string;
    anniversaryDate: string;
    assetValue: number;
    cashLoan: string;
    city: string;
    companyName: string;
    conversionActivityNumber: ConversionActivityNumber;
    conversionActivityType: string;
    country: string;
    createdBy: CreatedBy;
    //salesPerson: CreatedBy;
    createdDate: string;
    customerMaster: CustomerMaster;
    disbursedDate: string;
    disbursedFinanceAmount: string;
    district: string;
    dob: string;
    emailId: string;
    enquiryCropGrows: EnquiryCropGrow[];
    enquiryDate: string;
    enquiryMachineryDetails: CurrentMachineObj[];
    enquiryNumber: string;
    enquirySoilTypes: EnquirySoilType[];
    enquiryStatus: string;
    enquiryType: string;
    estimatedExchangePrice: number;
    estimatedFinanceAmount: number;
   // exchangeAmount: number;
    exchangeBrand: string;
    exchangeModel: string;
    exchangeRequired: string;
    fatherName: string;
    finalExchangePrice: number;
    financeLoggedInDate: string;
    financeStatus: string;
    financier: string;
    firstName: string;
    followUpType: string;
    generationActivityNumber: ConversionActivityNumber;
    generationActivityType: string;
    gstNo: string;
    id : number
    itemDescription: string;
    itemNo: string;
    language: string;
    lastModifiedBy: string;
    lastModifiedDate: string;
    lastName: string;
    middleName: string;
    mobileNumber: string;
    model: string;
    nextFollowUpDate: string;
    pancardNo: string;
    pinCode: number;
    postOffice: string;
    product: string;
    prospectCode: string;
    purposeOfPurchase: string;
    referralPersonName: string;
    remarks: string;
    retailConversionActivity: string;
    validationDate: string;
    variant: string;
    whatsAppNo: string;
    series: string;
    source: string;
    state: string;
    std: number;
    subModel: string;
    subsidy: string;
    subsidyAmount: number;
    tehsil: string;
    telephoneNo: string;
    tentativePurchaseDate: string;
    title: string;
    occupation : string,
    landSize : number,
  }

  export interface EnquirySaveDomain {
    conversionActivityNo: string;
    conversionActivitytype: string;
    createdBy: CreatedBy;
    itemDescription: string;
    enquiryNo: string;
    enquiryStatus: string;
    enquiryType: string;
    estimatedExchangePrice: number;
     // enquirySoilTypes: EnquirySoilType[];
    // enquiryCropGrows: EnquiryCropGrow[];
    // enquiryMachineryDetails: EnquiryMachineryDetail[];
    exchangeModel: string;
    exchangebrand: string;
    exchangeRequired: string;
    followUpType: string;
    generationActivityNo: number;
    generationActivityType: string;
    itemNo: string;
    model: string;
    product: string;
    customerMaster: ProspectDetailsObj;
    purposeOfPurchase: string;
    referralPersonName: string;
    remarks: string;
    retailConversionActivity: string;
   // salesPerson: CreatedBy;
    series: string;
    source: string;
    subModel: string;
    variant: string;
  }

  export interface EnquiryDetailsDomain {
    aadharNo: string;
    address1: string;
    address2: string;
    address3: string;
    alternateMobileNo: string;
    anniversaryDate: string;
    assetValue: number;
    cashLoan: string;
    city: string;
    companyName: string;
    conversionActivityNumber: ConversionActivityNumber;
    conversionActivityType: string;
    country: string;
    createdBy: CreatedBy;
   // salesPerson: CreatedBy;
    createdDate: string;
    prospectMaster?: any;
    disbursedDate: string;
    disbursedFinanceAmount: string;
    district: string;
    dob: string;
    emailId: string;
    enquiryCropGrows: EnquiryCropGrow[];
    enquiryDate: string;
    enquiryMachineryDetails: CurrentMachineObj[];
    enquiryNumber: string;
    enquirySoilTypes: EnquirySoilType[];
    enquiryStatus: string;
    enquiryType: string;
    estimatedExchangePrice: number;
    estimatedFinanceAmount: number;
   // exchangeAmount: number;
    exchangeBrand: string;
    exchangeModel: string;
    exchangeRequired: string;
    fatherName: string;
    finalExchangePrice: number;
    financeLoggedInDate: string;
    financeStatus: string;
    financier: string;
    firstName: string;
    followUpType: string;
    generationActivityNumber: ConversionActivityNumber;
    generationActivityType: string;
    gstNo: string;
    id : number;
    itemDescription: string;
    itemNo: string;
    language: string;
    lastModifiedBy: string;
    lastModifiedDate: string;
    lastName: string;
    middleName: string;
    mobileNumber: string;
    model: string;
    nextFollowUpDate: string;
    pancardNo: string;
    pinCode: number;
    postOffice: string;
    product: string;
    prospectCode: string;
    prospectType: string;
    purposeOfPurchase: string;
    referralPersonName: string;
    remarks: string;
    retailConversionActivity: string;
    validationDate: string;
    variant: string;
    whatsAppNo: string;
    series: string;
    source: string;
    state: string;
    std: number;
    subModel: string;
    subsidy: string;
    subsidyAmount: number;
    tehsil: string;
    telephoneNo: string;
    tentativePurchaseDate: string;
    title: string;
    occupation : string,
    landSize : number,
  }
  
  export interface CreatedBy {
    id : number
  }

  export interface ProspectMaster {
    id: number;
   prospectCode: string;
  }

  export interface GetEnquiryType{
    enquiryType: string;
  }

  export interface CustomerMaster {
    id: number;
   customerCode: string;
  // oldCustomerCode?:string
  }

  export interface ConversionActivityNumber {
    activityProposalId: number;
  }

  interface ViewEnquiryDomain {
    id: number;
    enquiryNumber: string;
    enquiryStatus: string;
    enquiryDate: string;
    salesPerson: ViewSalesPerson;
    referralPersonName: string;
    validationDate: string;
    enquiryType: string;
    followUpType: string;
    source: string;
    purposeOfPurchase: string;
    generationActivityType: string;
    generationActivityNumber: GenerationActivityNumber;
    retailConversionActivity: string;
    conversionActivityType: string;
    conversionActivityNumber: GenerationActivityNumber;
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
    prospectCode: string;
    prospectType: string;
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
    enquirySoilTypes: DropDownSoilType[];
    enquiryCropGrows: DropDownMajorCropGrown[];
    enquiryMachineryDetails: CurrentMachineObj[];
    prospectMaster: ProspectMaster;
    customerMaster: CustomerMaster;
    transferTo?: any;
    assetValue: number;
    cashLoan: string;
    financier: string;
    marginAmount : number;
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
    currentFollowUpDate:string
  }

  interface GenerationActivityNumber {
    activityNumber: string;
    activityProposalId: number;
  }
  // interface ConversionActivityNumber {
  //   activityNumber: string;
  //   activityProposalId: number;
  // }

 export interface ViewSalesPerson {
  lastName: string,
    id: number;
    salesPerson: string;
  }

  export interface GetActivityNumber {
    activityNumber: string;
    id: number;
    value: string;
  }
  
}


