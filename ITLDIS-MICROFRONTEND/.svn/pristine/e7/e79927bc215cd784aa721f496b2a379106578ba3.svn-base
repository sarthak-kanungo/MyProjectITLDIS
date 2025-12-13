import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class TrainingattendanceSheetSearchPageStore {
    private searchForm: FormGroup;

    constructor(private fb:FormBuilder){
        this.searchForm = fb.group({
            attendanceSearchHeader: this.attendanceSearchHeaderForm(),
        })
    }


    public get attendanceSearchForm(){
        return this.searchForm
    }

    attendanceSearchHeaderForm(){
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