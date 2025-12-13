import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class TrainingattendanceTrainingReportSearchPageStore {
    private searchForm: FormGroup;

    constructor(private fb: FormBuilder) {
        this.searchForm = fb.group({
            attendanceReportSearchHeader: this.attendanceReportSearchHeaderForm(),
        })
    }

    public get attendanceReportSearchForm() {
        return this.searchForm
    }

    attendanceReportSearchHeaderForm() {
        return this.fb.group({
            programNumber:[],
            fromDate: [],
            toDate: [],
        })
    }
}