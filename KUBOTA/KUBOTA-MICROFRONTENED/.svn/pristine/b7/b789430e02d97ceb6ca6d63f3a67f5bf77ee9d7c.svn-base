import { UploadableFile } from "src/app/ui/file-upload/file-upload"

export interface AttendanceHeaders {
    programId: string,
    programDate:string,
    trainingTypeId:string,
    trainingModuleId:string,
    designation:string
    photos:any
    trainer1: string,
    trainer2: string,
    trainingLocation:string,
    dealerLocation:string,
    chassisNo: string,
    startDate:string,
    endDate:string,
    remarks:string,
    attendanceDtl:AttendanceDtl[]
    nominationEmpIndexs:GrowthIndex[]
    programNominationHdrId:number
    avgGrowthIndex:any,
    multipartFileList:UploadableFile[],
}

export interface AttendanceHeader {
    programNumber:string
    departmentName:string
    trainingLocationId:any
    trainingModuleId:string
    fromDate: string;
    toDate: string;
    page: number;
    size: number;
    avgGrowthIndex:any
    
}

export interface AttendanceDtl {
   // nomineeAttendanceDtlId:number
    programNominationDtlId: number
    trainingDate:string
    attendance:string,
   
}

export interface GrowthIndex{
    programNominationDtlId:number,
    preTest:number,
    postTest:number,
    growthIndex:number, 
    // avgGrowthIndex:number,
}
