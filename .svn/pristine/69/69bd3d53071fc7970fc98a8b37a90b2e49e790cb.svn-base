
export interface TpcHeader {
    programId: number
    programNumber: string
    programDate: string
    programLocationId: number
    trainingTypeId: number
    trainingModuleId: number
    location: string
    startDate: string
    endDate: string
    lastNominationDate: string
    // maxNoOfNominees:number
    noNominees: number
    maxNoOfNominees: number
    remarks: string
    active: string

    tpcDealerDetails: Array<TpcDealerDetails>
    tpcHolidayDetails: Array<TpcHolidayDetails>
}

export interface TpcDealerDetails {
    dealerId: number
}

export interface TpcHolidayDetails {
    holidayDate: string
}

export interface TpcSearchHeader {
    departmentName:string
    programNo: string
    trainingLocationId: string
    trainingModuleId: number
    fromDate: string;
    toDate: string;
    page: number;
    size: number;
}

export interface TpcSeachList {
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
export interface ProgramNumber {
    programNumber: string
    programNo:string
}

export interface ApeoveDetails{
    nominationStatus:string
    attendedStatus:string
    programNominationDtlId:number
}

export interface Abc{
    nomineesApproval:Array<ApeoveDetails>
}

export interface DateObj{
    date:string
    value:boolean
}



