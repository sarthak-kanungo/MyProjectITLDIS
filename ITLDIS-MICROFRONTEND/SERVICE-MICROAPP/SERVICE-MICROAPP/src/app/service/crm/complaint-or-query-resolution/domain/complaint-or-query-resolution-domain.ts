export interface ComplaintOrQueryResolutionSubmiteDto{
    complaintOrQueryResolutionEntity:SubmitJson[]
    formData:SubmitJson
}

export interface SubmitJson{
    id:any
    active:any
    select:string
    surveyName:string
    subModel:string
    surveyType:string
    noOfDays:number
}