
export interface UploadableFile{

}

export interface submitSpareDescripancyClaim {
    discrepancyClaim: Rfc;
    multipartFile: File;
    multipartFileList: UploadableFile[];
  }
  export interface Rfc {
    id:any;
    draftFlag:boolean;
    accpacSparePartInvoice:number;
    sparePartGrn:number;
    dealerMaster:number;
    dealerEmployeeMaster:number
    dataFilePath: string
    discrepancyClaimDtls: DiscrepancyClaimDtls[];
  
  }

  export interface DiscrepancyClaimDtls {
    sparePartMaster: SparePartMaster;
    quantity: number;
  }
  export interface SparePartMaster{
    itemNo:any;
    id:any;
  }
  

  