import { UploadableFile, UploadableVideo } from "src/app/ui/file-upload/file-upload";


export interface FieldCondition {
  id: string;
  fieldCondition: string;
}
export interface FailureType {
  id?: string;
  failureType: string;
}
export interface SoilType {
  soilType: string;
}
export interface CropGrown {
  cropGrown: string;
}
export interface PCRDomain {
  jobCardViewDto: JobCardView;
  jobCardPartDto: JobCardPart[];
  labourCharge: LabourCharge[];
  outSideLabourCharge: OutSideLabourCharge[];
  warrantyPeriod?:any
}
export interface JobCardView {
  model: string;
  chassisNo: string;
  engineNo: string;
  mobileNo: string;
  customerName: string;
  registrationNumber: string;
  customerConcern: string;
  mechanicObservation: string;
  dealerObservation: string;
  actionTaken: string;
  dateOfInstallation: string;
  jobCardNo: string;
  jobCardDate: string;
  machineMasterId: number;
  dateOfFailure: string;
  soldToDealer: string;
  budgetConsumed:number;
  serviceDealer: string;
  serviceDealerAddress: string;
  address: string;
  warrantyPcrImplements?: WarrantyPcrImplement[];
  sparePartRequisitionItemList?: SparePartRequisitionItemList[];
  labourChargeList?:LabourList[],
  outsideChargeList?:OutsideChargeList[],        
  warrantyPcrPhotos?: WarrantyPcrPhotos[];
  WarrantyPcrVideos?: WarrantyPcrVideos[];
  jobCardHistory?: JobCardHistory[];
  jobCardPartDto?: JobCardPart;
  serviceJobCard?: Id;
  draftFlag?: boolean;
  file?: UploadableFile[];
  machineInventoryId?: number;
  id?: number;
  pcrDate?: string;
  pcrNo?: string;
  status?: string;
  delayReason?:string;
  crop?: string;
  cropCondition?: string;
  failureType?: string;
  fieldCondition?: string;
  soilType?: string;
  dealerRemarks?: string;
  kaiRemarks?: string;
  specialApvRemarks?:string;
  pcrRemark?:string
  repeatFailureFlag?: boolean;
  failureCount?: number;
  totalHour?: number;
  goodwillNo?:  string;
  goodwillDate?:  string;
  goodwillReason?:  string;
  goodwillType?: string;
  allowVideoUpload?:boolean
}

export interface JobCardHistory {
  serviceType: string;
  dateIn: string;
  jobCardNo: string;
  jobCardDate: string;
  hours: number;
  pcrNo:string;
  pcrDate:string;
  status:string
}

export interface JobCardPart {
  sparePartRequisitionId?: number;
  id?:number
  partNo: string;
  claimQuantity: number;
  pcrQuantity?: number;
  failureDescription: string;
  description: string;
  approvedQuantity?: number;
  code?: string;
  failureId?: number; 
}

export interface FailurePart {
  description: string;
  id: number;
  value?: string;
  code: string;
}

export interface SparePartRequisitionItemList {
  id?: number;
  approvedQuantity?: number;
  warrantyMtPartFailureCode?: Id;
  pcrQuantity?: number;
}
export interface LabourList {
    labourChargeId: number;
    pcrAmount: number;
}
export interface OutsideChargeList {
    jobcodeId: number;
    pcrAmount: number;
}

export interface WarrantyPcrPhotos {
  id: number;
  fileName: string;
  fileType?: string;
  videoFlag?:boolean
}

export interface WarrantyPcrVideos {
  id: number;
  fileName: string;
  fileType?: string;
  videoFlag:boolean
}

export interface SubmitPCR {
  warrantyPcr: JobCardView;
  multipartFileList: UploadableFile[];
  multipartVideoList: UploadableVideo[];
}

export interface Id {
  id: number;
}

export interface SearchWarrantyPcr {
  chassisNo: string;
  engineNo: string;
  jobCardFromDate: string;
  jobCardNo: string;
  jobCardToDate: string;
  machineModel: string;
  page: number;
  pcrFromDate: string;
  pcrNo: string;
  pcrToDate: string;
  size: number;
  status: string;
}

export interface SearchWarrantyList {
  id: number;
  pcrNo: string;
  status: string;
  pcrDate: string;
  jobCardNo: string;
  chassisNo: string;
  engineNo: string;
  model: string;
  jobCardDate: string;
  approve: string;
  dealerName:string;
  branch:string;
  village:string;
  mobileNo:string;
  customerName:string;
  serviceDealerCode:string;
  serviceDealerName:string;
  serviceDealerState:string;
  approvalRemark:string
  serviceHours:string;
  lastApprovedBy:string
}

export interface ApproveQuantity {
  kaiRemarks: string;
  rating: number,
  reason:string,
  approvalStatus : string;  
  pcrApprovalParts: ApprovalParts[];
  pcrApprovalLabours : ApprovalLabourORCharges[];
  pcrApprovalOutsideCharges : ApprovalLabourORCharges[];
  allowVideoUpload:boolean;
  managementCheck:boolean;
  warrantyPcrId: number;
  delayReason?:string;
}

export interface ApprovalParts {
  approvedQuantity: number;
  id: number;
}
export interface ApprovalLabourORCharges {
    approvedAmount: number;
    id: number;
  }

//  ========= View PCR Domain ==============

export interface ViewPcr {
  warrantyPcrViewDto: ViewPCRDomain;
  labourCharge: LabourCharge[];
  outSideLabourCharge: OutSideLabourCharge[];
  warrantyPart: WarrantyPart[];
  approvalDetails ?: any[];
  goodwillEnable?:any;
  files?:WarrantyPcrPhotos[];
  warrantyPeriod?:any;
}
export interface ViewPCRDomain {
  soilType: string;
  serviceJobCard: ServiceJobCard;
  pcrDate: string;
  crop: string;
  cropCondition: string;
  failureType: string;
  fieldCondition: string;
  dealerObservation: string;
  actionTaken: string;
  dealerRemarks: string;
  warrantyPcrImplements: WarrantyPcrImplement[];
  warrantyPcrPhotos: WarrantyPcrPhotos[];
  WarrantyPcrVideos?: WarrantyPcrVideos[];
  sparePartRequisitionItem?: SparePartRequisitionItem[];
  kaiRemarks: string;
  specialApvRemarks?: string;
  pcrNo: string;
  wcrNo?:string;
  status: string;
  delayReason?:string;
  id: number;
  repeatFailure: boolean;
  noOfTimes: number;
  goodwillFlag: boolean;
  soldToDealer:string;
  serviceDealer:string;
  serviceDealerAddress:string;
  allowVideoUpload?:boolean;
  createWcr?:boolean
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
export interface ServiceJobCard {
  customerMaster: CustomerMaster;
  jobCardDate: string;
  registrationNumber: string;
  customerConcern: string;
  mechanicObservation: string;
  machineInventory: MachineInventory;
  totalHour: number;
  jobCardNo: string;
  dateOfFailure: string;
  invoicedFlag?:boolean
  status: string;
  id?:number;
}

export interface CustomerMaster {
  lastName: string;
  id: number;
  customerName: string;
  mobileNo: string;
  address: string;
}

export interface ServiceJobCardDealerMaster {
  id: number;
  serviceDealer: string;
  serviceDealerAddress: string;
}

export interface MachineInventory {
  machineMaster: MachineMaster;
  chassisNo: string;
  engineNo: string;
  vinId: number;
  dateOfInstallation: string;
  registrationNumber: string;
}

export interface MachineInventoryDealerMaster {
  id: number;
  soldToDealer: string;
}

export interface MachineMaster {
  model: string;
  id: number;
}
export interface WarrantyPart {
  sparePartRequisitionId: number;
  approvedQuantity?: number;
  description: string;
  sparePartMasterId: number;
  complaintDescription: string;
  claimQuantity: number;
  rejectedQuantity?:number;
  pcrQuantity?: number;
  partNo: string;
  code: string;
  failureId: number;
  gwClaimApprovedQuantity?: number;
  gwClaimQuantity?: number;
  gwApprovedPercent?: number;
  priceType?: string;
  hodLogin?:boolean
}
export interface SparePartRequisitionItem {
  sparePartMaster: SparePartMaster;
  approvedQuantity: number;
  warrantyMtPartFailureCode: WarrantyMTPartFailureCode;
  id: number;
  claimQuantity: number;
}

export interface SparePartMaster {
  id: number;
  partNo: string;
  description: string;
}

export interface WarrantyMTPartFailureCode {
  code: string;
  complaintDescription: string;
  id: number;
}

export interface WarrantyPcrImplement {
  id?: number;
  implementCategory: string;
  implement: string;
  implementMake: string;
  implementSerialNumber: string;
  gearRatio: string;
  engineRpm: number;
  percentOfUsage: number;
  usageFailure: boolean;
}

//  ========= PCR Search Field Domain ==============

export interface SearchPCRAutoComplete {
  id: number;
  code: string;
  value: string;
}
export interface SearchByModel {
  id: number;
  model: string;
}
export interface PcrStatusModel {
  id: number;
  status: string;
}

// =========== Update PCR ==============

export interface SubmitUpdatePcr {
  warrantyPcr: UpdatePcr;
  multipartFileList: UploadableFile[];
  multipartVideoList: UploadableVideo[];
}
export interface UpdatePcr {
  id: number,
  dealerRemarks: string,
  serviceJobCard: UpdateServiceJobCard
}

export interface UpdateServiceJobCard {
  id: number;
  sparePartRequisitionItem: SparePartRequisitionItemList[];
  labourCharge: LabourList[];
  outsideJobCharge: OutsideChargeList[];
}

export interface AutoDealerCodeSearch {
  id: number,
  code: string,
  name: string,
  displayString: string
}