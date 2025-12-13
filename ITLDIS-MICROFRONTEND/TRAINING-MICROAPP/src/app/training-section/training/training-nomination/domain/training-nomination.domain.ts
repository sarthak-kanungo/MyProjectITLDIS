
export interface NominationHeader {

    nominationId:number
    nominationNumber:string
    nominationDate:string
    programId:number
    dealerId:number
    status:string
    nominationDetails: Array<NomineeDetails>
}

export interface NomineeDetails{
    nominationDtlId:number
    nominationId:number
    employeeId:number
    status:string
    attended:string
    tShirtSize:string
    active:string
    createdBy:number
    createdDate:string

}
export interface NomineeDetailsPatch{
    nominationDtlId:number
    designation:Array<DesignationJson>
    active: boolean
    name: Nominee
    empCode: string
    employeeCode: string
    status: any
    attended: boolean
    tShirtSize:string
    createdBy:number
    createdDate:string

}
export interface Nominee{
    employeeCode: string
    employeeName: string
    id: number
}

export interface DesignationJson{
    code: string
    displayString: string
    id: number
    name: string
}

export interface NominationSearchHeader {
    programId: string
    nomineeId: string
    fromDate: string;
    toDate: string;
    page: number;
    size: number;
}

export interface TncSeachList {
    programId: number
    edit: string
    programNumber: string
    trainingLocDesc: string
    trainingModuleDesc: string
    location: string
    startDate: string
    endDate: string
    lastNominationDate: string
    noOfAllowedNominees: string
    createdDate: string
    nominee: string
    approval: string
}
export interface employeeName{
    
designationId:any
employeeCode:any;
employeeName:any
id:any
}