import { Injectable } from "@angular/core";
import { TrainingattendanceTrainingReportSearchPageStore } from './attendance-training-report-search-page.store';
import { FormGroup } from '@angular/forms';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';

@Injectable()
export class TrainingattendanceTrainingReportSearchPagePresenter {
    readonly attendanceReportSearchForm: FormGroup


    constructor(private store:TrainingattendanceTrainingReportSearchPageStore){
        this.attendanceReportSearchForm = store.attendanceReportSearchForm
    }

    public get attendanceReportSearchHeaderForm(){
        return this.attendanceReportSearchForm.get('attendanceReportSearchHeader') as FormGroup
    }
   
}