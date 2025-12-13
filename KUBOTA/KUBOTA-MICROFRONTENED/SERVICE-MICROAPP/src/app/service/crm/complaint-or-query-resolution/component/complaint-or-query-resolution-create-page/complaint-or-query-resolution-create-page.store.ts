import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class ComplaintOrQueryResolutionCreatePageStore {
    private readonly _complaintOrQueryResolutionForm: FormGroup;
    constructor(private fb:FormBuilder){
        this._complaintOrQueryResolutionForm= fb.group({
            searchcomplaintOrQueryResolutionFormArray : new FormArray([])
        })
    }

    public get complaintOrQueryResolutionForm(){
        return this._complaintOrQueryResolutionForm
    }

    public buildsearchcomplaintOrQueryResolutionFormArray(){
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