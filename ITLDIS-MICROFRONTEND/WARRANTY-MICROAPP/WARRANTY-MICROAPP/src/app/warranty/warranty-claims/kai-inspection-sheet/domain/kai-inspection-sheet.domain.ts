import { UploadableFile } from "src/app/ui/file-upload/file-upload";


export interface KaiInspectionSheet {
  wcrDcDetail?: WarrantyDeliveryChallanView;
  kaiInspectionSheetView?: KaiInspectionSheetView;
  labourCharge: LabourCharge[];
  outSideLabourCharge: OutSideCharge[]
  warrantyPart: WarrantyPart[];
  // warrantyWcrView: WarrantyWcrView;
}

export interface KaiInspectionSheetView {
  id:number;
  kaiInspectionSheetPhoto: KaiInspectionSheetPhoto[];
  failureMode: KaiDropDown;
  failureUnit: KaiDropDown;
  typeOfUse: KaiDropDown;
  symptom: string;
  defect: string;
  remedy: string;
  remark: string;
  inspectionNo:string;
  inspectionDate:Date;
}
export interface KaiInspectionSheetPhoto {
  id: number;
  fileName: string;
}
export interface LabourCharge {
    id?:number;
    labourCode?:string;
    labourDesc?:string;
    labourHour?:number;
    claimable?:boolean;
    inspectionRemark?:string;
    claimApprovedAmount?:number;
    finalApprovedAmount?:number;   
    labourChargeId?:number;
}
export interface OutSideCharge {
    id?:number;
    jobcode?:string;
    jobcodeDesc?:string;
    claimable?:boolean;
    inspectionRemark?:string;
    claimApprovedAmount?:number
    finalApprovedAmount?:number   
    jobcodeId?:number        
}

export interface WarrantyPart {
  description: string;
  id: number;
  complaintDescription: string;
  partNo: string;
  code: string;
  claimApprovedQuantity: number;
  failureId: number;
  approvedQuantity: number;
  inspectionRemark: string;
  claimApprovedAmount: number;
  sparePartMasterId: number;
  unitPrice: number;
  claimQuantity: number;
  claimable: boolean;
  keyPartNumber: boolean;
}
export interface WarrantyDeliveryChallanView {
  dcDate: string;
  docketNumber: string;
  numberOfBox: number;
  dispatchDate: string;
  transporterName: string;
  dcNo: string;
  id: number;

  wcrDate: string;
  dealerCode: string;
  address: string;
  wcrNo: string;
  dealerName: string;  
  wcrType:string;
}

export interface WarrantyDeliveryChallanViewDealerMaster {
  dealerCode: string;
  emailId: string;
  mobileNo?: null;
  id: number;
  serviceDealer: string;
  serviceDealerAddress: null;
  mobileNumber?: string;
}

export interface WarrantyDeliveryChallanViewSparePartRequisitionItem {
  warrantyWcr: WarrantyWcr;
  amount: number | null;
  priceUnit: number;
  gstPercent: number;
  sparePartMaster: SparePartMaster;
  gstAmount: number;
  totalAmount: number;
  hsnCode: string;
  approvedQuantity: number;
  reqQuantity: number;
  id: number;
}

export interface SparePartMaster {
  igstPercent?: number;
  spmrp: number;
  id: number;
  itemNo: string;
  description: string;
}

export interface WarrantyWcr {
  wcrNo?: string;
  wcrType?:string;
  id: number;
}

export interface WarrantyWcrView {
  kaiRemark: string;
  wcrDate: string;
  warrantyPcr: WarrantyPcr;
  inspectionRemark: string;
  dealerMaster: WarrantyDeliveryChallanViewDealerMaster;
  sparePartRequisitionItem: WarrantyWcrViewSparePartRequisitionItem[];
  wcrNo: string;
  wcrType: string;
  wcrStatus: string;
  id: number;
}

export interface WarrantyWcrViewSparePartRequisitionItem {
  amount: number | null;
  sparePartMaster: SparePartMaster;
  warrantyDeliveryChallan: WarrantyDeliveryChallan;
  approvedQuantity: number;
  id: number;
}

export interface WarrantyDeliveryChallan {
  dcNo: string;
  id: number;
}

export interface WarrantyPcr {
  pcrDate: string;
  dealerObservation: string;
  actionTaken: string;
  dealerRemarks: string;
  warrantyPcrImplements: any[];
  dealerMaster: WarrantyDeliveryChallanViewDealerMaster;
  sparePartRequisitionItem: any[];
  serviceJobCard: ServiceJobCard;
  failureType: string;
  pcrNo: string;
  kaiRemarks: string;
  status: string;
  id: number;
  repeatFailure: boolean;
  noOfTimes: number;
}

export interface ServiceJobCard {
  customerMaster: CustomerMaster;
  machineInventory: MachineInventory;
  customerConcern: string;
  labourCharge: any[];
  totalHour: number;
  jobCardDate: string;
  jobCardNo: string;
  dateOfFailure: string;
  dateOfRepair: null;
}

export interface CustomerMaster {
  lastName: string;
  id: number;
  customerName: string;
  address: string;
  mobileNo: string;
}

export interface MachineInventory {
  dealerMaster: MachineInventoryDealerMaster;
  chassisNo: string;
  engineNo: string;
  installationDate: null;
  machineMaster: MachineMaster;
  id: number;
}

export interface MachineInventoryDealerMaster {
  id: number;
  solDealer: string;
}

export interface MachineMaster {
  model: string;
  id: number;
}

export interface MaterialDetails {
    itemNo: string;
    itemDescription: string;
    unitPrice: number;
    quantity: number;
    value: number;
    approvedQty: number;
    approvedVal: number;
    inspectionRemark: string;
    claimable: boolean;
    keyPartNumber: boolean;
    id: number;
    sparePartMaster: SparePartMaster;
}

//////////////////////// Submit JSON ///////////////////////////

export interface SubmitKaisheet {
  kaiInspectionSheet: KaiSheet;
  multipartFileList: UploadableFile[];
}

export interface KaiSheet {
  id : number;
  failureMode:              KaiDropDown;
  failureUnit:              KaiDropDown;
  typeOfUse:                KaiDropDown;
  symptom:                  string;
  defect:                   string;
  remedy:                   string;
  claimFinalRemark:         string;
  actionType: string;
  warrantyWcr:              WarrantyWcr;
  sparePartRequisitionItem: SparePartRequisitionItem[];
  labourJobCharge:          LabourCharge[];
  outsideJobCharge:         OutSideCharge[];
}

export interface JobCharge {
  id:                number;
  serviceMtFrsCode?: WarrantyWcr;
  inspectionRemark:  string;
  claimable:         boolean;
  serviceMtJobcode?: WarrantyWcr;
}

export interface SparePartRequisitionItem {
  id: number;
  claimApprovedAmount: number;
  claimApprovedQuantity: number;
  inspectionRemark: string;
  claimable: boolean;
  keyPartNumber: boolean;
}

export interface SparePartMaster {
  id:     number;
  itemNo: string;
}

////////////////////// Search Inspection Sheet ////////////////

export interface SearchInspectionSheet {
  inspectionNo:string;
  fromDate:string;
  toDate:string;
  page: number;
  size: number;
}
export interface SearchDetail {
  inspectedBy: string;
  wcrNo: string;
  wcrDate: string;
  wcrType: string;
  wcrStatus: string;
  inspectionNo: string;
  inspectedDate: string;
}

export interface KaiDropDown {
  id: number;
  code: string;
  value: string;
  activeStatus?: string;
}