export interface MobileNo {
    mobileNumber: string,
    id: number
}



export interface SubmitDirectSurveyForm{
    surveyHeader: SurveyHeader
    machineDetails:any
    callAttempt:CallAttempt[]
    surveyQuestions:any
    subAns:any[]
    complaint:any
    complaintRecording:any
    callRecording:any
    freeServiceHistory:any
    directSurveyHistory:any
    implementDetails:any
    crops:any
    otherPurchaseDetails:any
}

export interface UploadAudio {
    id : string
    previewUrl : string | any
    file : File
}


export interface SurveyHeader{
    surveyId: any
    surveyRemdId: number
    surveyTypeId: any
    vinId: any
    dateOfInstallation: any
    warranyValidTill: any
    surveyNo: any
    surveyDate: any
    surveyStatus: any
    surveyDoneBy: number
    surveyLocation: any
    soldDealerName: any
    customerMasterId: number
    customerCode: string
    customerName: string
    customerMobileNumber: any
    customerAlternateNo: any
    customerAddress: any
    country: any
    state: any
    district: any
    tehsil: any
    city: any
    postOffice: any
    pinCode: any
    panNumber: any
    gstNo: any
    preferedLanguage: any
    satisfactionLevel: any
    contactPersonName: any
    contactPersonMobileNo: any
    contactPersonProfileId: any
    contactPersonAge: any
    ageOfMachineInMonth: Number
    hoursMeterReading: any
    firstTimeBuyer: any
    brandModel: any
    factorInfluencedId: any
    
    refCustomerName1: any
    refCustomerMobileNo1: string
    refCustomerAddress1: any
    refCustomerVillage1: any
    refCustomerName2: any
    refCustomerMobileNo2: string
    refCustomerAddress2: any
    refCustomerVillage2: any
    additionalComments: any

    callAttempt:CallAttempt[]
    surveyQuestions:QuestinAns[]
    complaint:complaintMain[]
    implementDetails:any
    crops:any[]
    otherPurchaseDetails:any[]
    soldToDealerId:number

}
export interface CallAttempt{
    surveyRemdId: number
    callRemarks:any
    callDate:any
    responseTypeId:any
    callTime:any
    createdBy:number
}

export interface QuestinAns{
    questionDesc:any
    quesId:number
    mainAnswer:any
    quesDtlId:number
    subAnswer:any[]
    remarks:any
}

export interface complaintMain{
    complaintNo:string
	department:string
	typeOfComplaint:string
	description:string
	actionTaken:string
}
export interface SubAns{
    subAnswer:any
}

export interface FirstTimeBuyer {
    value: string;
    viewValue: string;
}

  export interface VillageSearch {
    villageId: any,
    villageName?: string,
    value:string
}

export interface SoldToDealer {
    id: number,
    code?: string,
    displayString:string
}