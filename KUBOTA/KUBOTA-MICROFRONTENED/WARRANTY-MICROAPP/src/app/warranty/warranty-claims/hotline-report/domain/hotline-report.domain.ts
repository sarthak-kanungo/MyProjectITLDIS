import { UploadableFile } from "src/app/ui/file-upload/file-upload"

export interface SearchAutocomplete{
    id?: number
	value?: string
	code?: string
	partNumber?: string
	itemNo?: string
}
export interface AutoCompleteResult {
    code?: string;
    value?: string;
    machineInvetoryId?: number;
    id?:number;
    failureCode?:        any
    
    failureDescription?:   any
  }
  export interface CompleteChassis{
    id:any
    chassisNo:string
    itemDescription: string;
    
  }
  export interface PartDetails {
    itemNo: string;
    itemDescription: string;
    
  }
  export interface MaterialData{
    isSelected: boolean,
    chassisNo: string,
    itemDescription:string,
    vendorCode: string,
    vendorName: string,
    vendorInvoiceNo: string,
    vendorInvoiceDate:any;
    id:any
     containerNo: string,
     isClaimmable:any
    machineVinMaster:string
    machineMaster:machineMaster
  }


  export interface PartData{
    id:any
    isSelected: boolean,
    // sparePartMaster: string,
    partDescription: string,
    quantity: number,
    claimableToVendor: boolean,
    sparePartMaster:any
    isClaimmable:any
  }
  export interface autoCompleteChassis{
    chassiNo:string,
    id:number;
  }
  export interface searchHotline {
    page: number;
    size: number;
    hotlineNo: string;
   status: string;
    endDate: string;
    startDate:string
  }
  export interface submitHTR {
    status:any
    hotlineReportNo:string
    warrantyHotlineReport: Htr;
    // responseFromVendor:any;
    htlrNo:any;
    multipartFile: File;
    multipartFileList: UploadableFile[];
  }
  export interface Htr {
    failuredate: string;
    dataFilePath: string
    status:any
    id:any
    // for patch
    htlrNo:any
    hotlineNo:any;
    // responseFromVendor:any
    hotlinePlantMaster:any
    conditionFailureMaster:any;
    departmentMaster:any;
    employeeMaster:any;
    dealerDepotMapping:any
    failureType:any;
    complaint:any,
    remarks:any,
    failureDate:any
    // 
    hotlineReportMachineDetails: warrantyHotlineMachineReport[];
    hotlineReportPartsDetails:warrantyHotlinePartReport [];
    hotlineReportAttachments?: warrantyPcrPhotos;
  }
  export interface warrantyHotlineMachineReport {
    machineVinMaster: machineVinMaster;
    machineMaster:machineMaster
    vendorCode: string,
    vendorName: string,
    vendorInvoiceNo: string,
    vendorInvoiceDate
     containerNo: string,
     id: number
  }
  export interface machineMaster{
    itemDescription:any
  }
  export interface warrantyPcrPhotos {
    id: number;
    fileName: string;
    fileType?: string;
    
  }
  export interface hotlineReportMachineDetails{
    machineVinMaster: machineVinMaster;
    vendorCode: string,
    vendorName: string,
    vendorInvoiceNo: string,
    vendorInvoiceDate: string,
     containerNo: string,
  }
  
  export interface warrantyHotlinePartReport {
    sparePartMaster:sparePartMaster
    quantity:any
    itemDescription:any
    isClaimmable:any
    id:number
  }
  export interface sparePartMaster{
    id:number,
    itemNo: number,
    itemDescription:string
  }
  
  export interface machineVinMaster {
    id: number;
    chassisNo: string;
    itemDescription?: string;
    value?: string;
  }
