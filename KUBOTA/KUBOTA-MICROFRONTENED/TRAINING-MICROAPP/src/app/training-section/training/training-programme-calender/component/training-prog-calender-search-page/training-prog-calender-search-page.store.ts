import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class TrainingProgrammeCalenderSearchPageStore {
    private _tpcSearchForm:FormGroup

    constructor(private fb:FormBuilder){
        this._tpcSearchForm = fb.group({
            tpcSearchHeader: this.tpcSearchHeaderForm(),
        })
    }


    public get tpcSearchForm(){
        return this._tpcSearchForm
    }

    tpcSearchHeaderForm(){
        return this.fb.group({
            programNumber:[],
            trainingLocation:[],
            typeofTraining:[],
            trainingModule:[],
            fromDate:[],
            toDate:[],
        })
    }
   
}