import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";

@Injectable()
export class QuestionMasterSearchPageStore{

    private _questionMasterSearchForm:FormGroup

    constructor(private fb:FormBuilder){
        this._questionMasterSearchForm = fb.group({
            questionMasterSearchHeader: this.questionMasterSearchHeaderForm(),
        })
    }


    public get quesMasterSearchForm(){
        return this._questionMasterSearchForm
    }

    questionMasterSearchHeaderForm(){
        return this.fb.group({
            questionCode:[],
            questionType:[],
            fromDate:[],
            toDate:[],
        })
    }
}