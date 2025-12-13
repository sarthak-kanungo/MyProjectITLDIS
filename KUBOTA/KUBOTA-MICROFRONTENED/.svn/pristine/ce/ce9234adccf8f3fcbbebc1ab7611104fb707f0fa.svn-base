import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";

@Injectable()
export class ModelSurveyMasterSearchPageStore{
    private readonly _modelSurveyMasterSearchForm: FormGroup;
    constructor(private fb:FormBuilder){
        this._modelSurveyMasterSearchForm=fb.group({
            modelSurveyMasterSearchForm: this.buildModelSurveyMaster(),
        })
    }

    public get modelSurveySearchForm(){
        return this._modelSurveyMasterSearchForm
    }

    public buildModelSurveyMaster(){
        return this.fb.group({

        })
    }

}