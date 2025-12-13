import { UploadableFile } from 'itldis-file-upload';

export interface AutoActivityNo {
  activityNumber: string;
  value: string
  id: number
}

export interface DropDownActivityType {
  activityType: string;
  id: number
}

export interface TargetedProductDropDown {
  id: number;
  product: string;
}

export interface HeaderDetailsByActivityNo {
  location: string;
  toDate: string;
  noOfMachines: number;
  fromDate: string;
  activityCreatedDate: string;
  status: string;
  targetedNumbers: number;
  product: string;
  activityType: string;
}

export interface JobCardDetailsByActivityNo {
  model: string;
  serviceType: string;
  chassisNo: string;
  closedDate?: any;
  jobCardDate: string;
  customerName: string;
  customerCode: string;
  mechanicName: string;
  jobCardNo: string;
  id: number
  labourCharges: number
  sparePartSale: number
  machineInDate: string;
}

export interface MachineDetailsByActivityNo {
  id: number;
  series: string;
  noOfMachines: number;
}

export interface ServiceDetailsByActivityNo {
  id: number
  serviceType: string;
  serviceTypeCount: number;
}

export interface FilterSearchServiceActivityReport {
  activityNumber?: string;
  activityStatus?: string;
  activityType?: string;
  activityDateTo?: string;
  activityDateFrom?: string;
  activityEffectiveness?: string;
  targetedproduct?: string;
  page: number;
  size: number;
  orgHierId:number;
}

export interface ActivityEffectiveness {
  id: number;
  effectiveness: string;
}

export interface SubmitServiceActivityReport {
  serviceActivityReport: ServiceActivityReport,
  multipartFileList: File[]
}

export interface MachineInventory {
  id: number;
}

export interface ServiceActivityReport {
  activityEffectiveness: string;
  actualFromDate: string;
  actualToDate: string;
  remarks: string;
  serviceActivityProposal: DealerEmployeeMaster;
  serviceActivityReportMachineDetails: ServiceActivityReportMachineDetail[];
  serviceActivityReportServiceDetails: ServiceActivityReportServiceDetail[];
  serviceActivityReportJobCardDetails: ServiceActivityReportJobCardDetail[];
  // serviceActivityReportPhotos : File[]
}

export interface ServiceActivityReportServiceDetail {
  noOfMachine: number;
  serviceMtServiceTypeInfo: DealerEmployeeMaster;
}

export interface ServiceActivityReportMachineDetail {
  machineSeries: DealerEmployeeMaster;
  noOfMachine: number;
}

export interface ServiceActivityReportJobCardDetail {
  serviceJobCard: DealerEmployeeMaster;
  labourCharges: number;
  sparePartSale: number;
  // lubricants?: number;
}

export interface DealerEmployeeMaster {
  id: number;
}

export interface ViewServiceActivityReport {
  activityReportHeaderData: ActivityReportHeaderData;
  activityMachineReport: MachineDetailsByActivityNo[];
  activityServiceReport: ServiceDetailsByActivityNo[];
  activityJobCardReport: JobCardDetailsByActivityNo[];
  activityReportPhotoList : ActivityReportPhotoList[]
}

export interface ActivityReportHeaderData {
  location: string;
  fromDate: string;
  activityReportDate: string;
  activityNumber: string
  targetedNumbers: number;
  product: string;
  activityType: string;
  toDate: string;
  actualFromDate: string;
  id: number;
  noOfMachines: string
  activityEffectiveness: string;
  remarks: string;
  status: string;
  actualToDate: string;
  activiyCreationDate: string;
}

export interface ActivityReportPhotoList {
  fileName: string;
}