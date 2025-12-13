import { Injectable } from "@angular/core";
import { TrainingProgrammeCalenderSearchPageStore } from './training-prog-calender-search-page.store';
import { FormGroup } from '@angular/forms';

@Injectable()
export class TrainingProgrammeCalenderSearchPagePresenter {

    readonly tpcSearchForm: FormGroup


    constructor(private store:TrainingProgrammeCalenderSearchPageStore){
        this.tpcSearchForm = store.tpcSearchForm
    }

    public get tpcSearchHeaderForm(){
        return this.tpcSearchForm.get('tpcSearchHeader') as FormGroup
    }

   
}