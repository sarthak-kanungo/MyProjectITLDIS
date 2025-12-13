
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { WarrantyPcrPhotos } from '../../product-concern-report/domain/product-concern-report.domain';

export interface Id {
  id: number;
}
export interface VinId {
  vinId: number;
}
export interface LogsheetType {
  id: number;
  logsheetType: string;
}

export interface AutoCompleteResult {
  code?: string;
  value?: string;
  machineInvetoryId?: number;
  id?:number;
  failureCode?:        any
  failureDescription?:   any
}
export  interface FailureCode{
  failureCode?:        any
  failureDescription?:   any
  keyPartNumber?:boolean 
  sparePartMasterId:any
}

export interface PartDetails {
  itemNo: string;
  itemDescription: string;
  mrp: number;
  id: number;
}

export interface LogSheet {
  id: number;
  model:               string;
  customerName:        string;
  seviceBookingId:     number;
  address:             string;
  machineInventoryId:  number;
  engineNo:            string;
  registrationNumber:  string;
  code:                string;
  mobileNumber:        string;
  customerId:          number;
  totalHour:           string;
  installationDate:    string;
  dateOfInstallation: string;

  soldToDealer:        string;
  qaRemarks:        string;
  serviceRemarks:     string;
  jobCardDate:          string;
  customerConcern:      string;
  mechanicObservation:  string;
  chassisNo:            string;
  mobileNo:             string;
  jobCardNo:            any;
  dateOfFailure:        string;
  dealerObservation:    string;
  actionTaken:          string;
  dealerRemarks:        string;
  serviceDealer:        string;
  serviceDealerAddress: string;
  pcrNumber:              string;
  machineMasterId:      number;
  linkjobCardNo:          any;
  // jobCardPartDto: JobCardPart[];
  logsheetImplements: LogsheetImplements[];
  logsheetFailurePartInfo: LogsheetFailurePartInfo[];
  logsheetType: string;
  draftFlag: boolean;
  machineInventory?: VinId;
  customerMaster?: Id;
  serviceJobCard?: Id;
  hours: string;
  crop: string,
	cropCondition: string;
	failureType: string;
	soilType: string;
	fieldCondition: string;
	probableCause: string;
	resultOfConfirmation: string;
	tentativeAction: string;
  remarks: string;  
  logsheetNo?: string;
  logsheetDate?: string;
  status?: string;
  noOfTime?: boolean;
  repeatFailure?: string;
  failureDate?: string;
}


export interface LogSheetJobResult {
  jobCardViewDto: LogSheet;

  jobCardPartDto: JobCardPart[];
}

export interface JobCardPart {
   failureCode?:     any| FailureCode;
  claimQuantity?:      number;
  partNo?:             string | SparePartMaster;
  id:                 number;
  description?: string;
  sparePartMaster?:   string | SparePartMaster;
  itemNo?:            string;
  failureDescription?:any
  failureDescriptions?:any
  keyPartNumber?:boolean 
  sparePartMasterId:any;
  
}

export interface LogsheetImplements {
  implementCategory: string;
	implement: string;
	implementMake: string;
	implementSerialNumber: string;
	gearRation: string;
	engineRpm: number;
	percentOfUsage: number;
  usageFailure: boolean;
  id?:number;
}

export interface LogsheetFailurePartInfo {
  id?:number
  sparePartMaster: SparePartMaster;
  quantity: number;
  failureCode: string;
  failureDescription:any
  keyPartNumber?:boolean 
  sparePartMasterId?:any;
  failureDescriptions?:any
}

export interface SparePartMaster {
  id: number;
  itemNo: string;
  itemDescription: string;
  value?: string;
  partsNo?:any;
  failureCode?:string;
  failureDescription?:any
 
}

export interface WarrantyLogSheetPhotos {
  id: number;
  fileName: string;
}

export interface SubmitLogSheet {
  warrantyLogsheet: LogSheet;
  multipartFileList: UploadableFile[];
}


export interface SearchWarrantyLogSheetList {
  model: string;
  jobCardDate: string;
  registrationNumber: string;
  installationDate: string;
  logsheetDate: string;
  dateOfFailure: string;
  crop: string;
  cropCondition: string;
  fieldCondition: string;
  soldToDealer: string;               
  serviceDealer: string;                        
  soilType: string;
  chassisNo: string;
  customerName: string;
  jobCardNo: string;
  logsheetType: string;
  failureType: string;
  serviceDealerCity: string;
  status: string;
  hours: string;
  id: number;
  address: string;
}

export interface SearchWarrantyLogSheet {
  chassisNo: string;
  engineNo: string;
  jobCardFromDate: string;
  jobCardNo: string;
  jobCardToDate: string;
  machineModel: string;
  page: number;
  size: number;
  status: string;
  logsheetNo: string;
  logsheetType: string;
  logsheetFromDate: string;
  logsheetToDate: string;
  failureType: string;
  mobileNo: string;
  registrationNo: string;
}

// ============= Logsheet View Domain =============== //

export interface ViewLogSheet {
  id : number;
  remarks:                 string;
  soilType:                string;
  logsheetType:            string;
  failureType:             string;
  customerMaster:          string;
  machineInventory:        MachineVinMaster;
  logsheetNo:              string;
  logsheetDate:            string;
  qaRemarks:                string;
  serviceRemarks:            string;
  failureDate:            string;
  crop:                    string;
  cropCondition:           string;
  fieldCondition:          string;
  probableCause:           string;
  resultOfConfirmation:    string;
  tentativeAction:         string;
  status:                  string;
  hours:                   string;
  serviceJobCard:          ServiceJobCard;
  jobCardNo:                  string;
  logsheetImplements:      LogsheetImplements[];
  logsheetFailurePartInfo: SparePartRequisitionItem[];
  warrantyLogsheetPhotosList: WarrantyPcrPhotos;
  partNo :string
 
}

export interface ServiceJobCard {
  jobCardNo:                string;
  pcrNo: string;
  soldDealer: string;
  serviceDealer:     string;
  serviceDealerCity: string;
  customerMaster:           CustomerMaster;
  machineInventory:         MachineVinMaster;
  sparePartRequisitionItem: SparePartRequisitionItem[];
  jobCardDate:              string;
  installationDate:              string;
  registrationNumber:string
  id:                       number;
}

export interface CustomerMaster {
  firstName: string;
  lastName:  string;
  address1:  string;
  id:        string;
}

export interface MachineInventory {
  machineVinMaster: MachineVinMaster;
  id:            number;
}

export interface MachineVinMaster {
  vinId:           number;
  chassisNo :string
  registrationNumber:string
  engineNo:string
  machineMaster: MachineMaster;
}

export interface MachineMaster {
  model: string;
  id:    number;
}

export interface SparePartRequisitionItem {
  quantity:     number;
  reqQuantity?:number;
  sparePartMaster: SparePartMaster;
  id:              number;
  failureCode: string;
  failureDescription:any
  keyPartNumber?:boolean 
  partNo :string
  sparePartMasterId:any
 
}

export interface SparePartMaster {
  itemNo: string;
  id:     number;
   failureDescriptions?:any
}


