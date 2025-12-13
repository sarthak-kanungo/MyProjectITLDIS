import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class ModelSurveyMasterCreatePageStore {
    private readonly _modelSurveyMasterForm: FormGroup;
    constructor(private fb:FormBuilder){
        this._modelSurveyMasterForm= fb.group({
            modelServeyFormArray : new FormArray([])
        })
    }

    public get modelSurveyForm(){
        return this._modelSurveyMasterForm
    }

    public buildmodelServeyFormArray(){
        return this.fb.group({
            active:[],
            select:[],
            surveyName:['',Validators.compose([Validators.required])],
            subModel:['',Validators.compose([Validators.required])],
            surveyType:['',Validators.compose([Validators.required])],
            //noOfDays:[],
            noOfDays:['',[Validators.required,CustomValidators.numberOnly]],
        })
    }
}