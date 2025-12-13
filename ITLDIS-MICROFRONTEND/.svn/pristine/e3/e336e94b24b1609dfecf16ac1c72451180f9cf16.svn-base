import { Injectable } from "@angular/core";
import { TrainingattendanceSheetSearchPageStore } from './attendance-sheet-search-page.store';
import { FormGroup } from '@angular/forms';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';

@Injectable()
export class TrainingattendanceSheetSearchPagePresenter {
    readonly attendanceSearchForm: FormGroup


    constructor(private store:TrainingattendanceSheetSearchPageStore){
        this.attendanceSearchForm = store.attendanceSearchForm
    }

    public get attendanceSearchHeaderForm(){
        return this.attendanceSearchForm.get('attendanceSearchHeader') as FormGroup
    }
}