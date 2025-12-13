export interface WcrDomain {
  pcrWarrantyClaimDto?: Wcr;
  goodwillViewDto?: Goodwill;
  warrantyWcrView?: ViewWcrByWcrNo,
  labourCharge: LabourCharge[];
  outSideLabourCharge: OutSideLabourCharge[];
  warrantyPart: WarrantyPart[];
  approvalHierDetails?:any
  isShowInspectionBtn?:boolean  
  invoiceType?:InvoiceType        
}
export interface InvoiceType{
    isDealerPaidInvoice:boolean;
    isKaiInvoice:boolean;
}
export interface Wcr {
  kaiRemarks: string;
  pcrNo: string;
  
  failureType: string;
  sparePartRequisitionItem: SparePartRequisitionItem[];
  
  serviceDealer: string;
  emailId: string;
  mobileNumber: string;
  serviceDealerAddress: string;
  dealerCode: string
  soldToDealer:string
    
  serviceJobCard: ServiceJobCard;
  pcrDate: string;
  dealerObservation: string;
  actionTaken: string;
  dealerRemarks: string;
  warrantyPcrImplements: any[];
  status: string;
  id: number;
  repeatFailure: boolean;
  noOfTimes: number;
  goodwillNo?: string;
  goodwillDate?:string
}
export interface Goodwill {
    kaiRemarks: string;
    goodwillNo: string;
    warrantyPcr : Wcr;
    status: string;
    id: number;
    goodwillDate:string
    emailId: string;
    mobileNumber: string;
    dealerCode: string
}
export interface WarrantyPcr{
    pcrNo:string
    pcrDate: string;
}

export interface LabourChargeSubmit {
    labourChargeId : number,
    claimApprovedAmount : number
}

export interface OutSideChargeSubmit {
    jobcodeId : number,
    claimApprovedAmount : number
}

export interface WarrantyPart {
  id: number;
  description: string;
  sparePartMasterId: number;
  complaintDescription: string;
  claimQuantity: number;
  partNo: string;
  code: string;
  failureId: number;
  claimApprovedAmount: number;
  claimApprovedQuantity: number;
  approvedQuantity: number;
  unitPrice: number;
}
export interface DealerMaster {
  id: number;
  serviceDealer: string;
  emailId: string;
  mobileNumber: string;
  serviceDealerAddress: string;
  dealerCode: string

}

export interface ServiceJobCard {
  jobCardNo: string;
  jobCardDate: string;
  totalHour: number;
  machineInventory: MachineInventory;
  customerMaster: CustomerMaster;
  customerConcern: string;
  dateOfFailure: string;
  dateOfRepair: string;
  id: number;
}

export interface CustomerMaster {
  lastName: string;
  id: number;
  mobileNo: string;
  customerName: string;
  address: string;
}

export interface MachineInventory {
  //dealerMaster: MachineInventoryDealerMaster;
  engineNo: string;
  chassisNo: string;
  machineMaster: MachineMaster;
  vinId: number;
  installationDate: string;
}

export interface MachineInventoryDealerMaster {
  id: number;
  soldToDealer: string;
}

export interface MachineMaster {
  model: string;
  id: number;
}

export interface SparePartRequisitionItem {
  approvedQuantity?: number;
  sparePartMaster?: SparePartMaster;
  amount?: number;
  id: number;
  claimApprovedQuantity?: number;
  claimApprovedAmount?: number;
}

export interface SparePartMaster {
  id: number;
  description: string;
  itemNo: string;
  spmrp: number;

}

export interface FailurePart {
  itemNo: string;
  description: string;
  approvedQuantity: number;
  claimValue: number;
  unitPrice: number;
  finalApprovedQuantity: number;
  finalApprovedAmount: number;
  id: number
}

export interface LabourCharge {
    
    labourChargeId:number,
    labourCode:string,
    labourDesc:string,
    labourHour:string,
    claimAmount?:number,
    pcrAmount?:number,
    approvedAmount?:number,
    acceptedPercentage?:number,
    goodwillApprovedAmount?:number,     
    goodwillClaimAmount?:number,
    rejectedAmount?:number        
}

export interface OutSideLabourCharge {
    jobcode:string,
    jobcodeId:number,
    jobcodeDesc:string,
    pcrAmount?:number,
    claimAmount?:number,
    approvedAmount?:number,
    acceptedPercentage?:number,
    goodwillApprovedAmount?:number,     
    goodwillClaimAmount?:number,
    rejectedAmount?:number
}
export interface WcrSubmit {
    
  warrantyPcr?: Id;
  warrantyGoodwill?: Id;
  kaiRemarks?: string;
  inspectionRemarks?: string;
  draftFlag?: boolean;
}

export interface Id {
  id: number;
}

export interface WcrDropdown {
  id: string;
  status?: string;
  wcr_type: string;
}
export interface WcrFinalstatus {
  id: string;
  final_status?: string;
 
}
export interface SearchWcr {
  model: string;
  jobCardNo: string;
  chassisNo: string;
  dealerCode: string;
  dealerName: string;
  dealerShip:any;
  dealerId:number;
  engineNo: string;
  customerMobileNo: string;
  customerName: string;
  dealerMobileNo: string;
  failureType: string;
  wcrNo: string;
  wcrType: string;
  pcrNo: string;
  installationDate: string;
  dateOfFailure: string;
  pcrDate: string;
  soldToDealer: string;
  workshopAddress: string;
  dateOfRepair: string;
  customerAddress: string;
  status: string;
  hour: string;
  page?: number;
  size?: number;
  pcrToDate?: string;
  pcrFromDate?: string;
  jobCardToDate?: string;
  jobCardFromDate?: string;
  wcrFromDate?: string;
  wcrToDate?: string;
  kaiInvoiceUpload?:string;
}

// VIEW WCR By wcrNo //

export interface ViewWcrByWcrNo {
  warrantyPcr?:      Wcr;
  warrantyGoodwill?: Goodwill;
  sparePartRequisitionItem: SparePartRequisitionItem[];
  kaiRemark:        string;
  wcrDate:          string;
  inspectionRemark: string;
  wcrNo:            string;
  wcrType:          string;
  wcrStatus:        string;
  id:               number;
}

