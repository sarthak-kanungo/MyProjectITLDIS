export interface MobileNo {
    mobileNumber: string,
    id: number
}
export interface ModelSurveyMasterSubmiteDto{
    modelSurveyMasterEntity:SubmitJson[]
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
export interface Model {
    model: any;
}
export interface AutoCompSubModel{
    subModel:any
}
export interface AutoCompVariant{
    variant:any
}