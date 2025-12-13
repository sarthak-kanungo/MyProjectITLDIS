import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class ComplaintCreatePageStore {
    private readonly _complaintForm: FormGroup;
    constructor(private fb:FormBuilder){
        this._complaintForm= fb.group({
            complaintFormArray : new FormArray([])
        })
    }

    public get complaintForm(){
        return this._complaintForm
    }

    public buildcomplaintFormArray(){
        return this.fb.group({
            // active:[],
            // select:[],
            // surveyName:[''],
            department:['',Validators.compose([Validators.required])],
            surveyType:['',Validators.compose([Validators.required])],
            //noOfDays:[],
            noOfDays:['',Validators.compose([Validators.required])],
        })
    }
}