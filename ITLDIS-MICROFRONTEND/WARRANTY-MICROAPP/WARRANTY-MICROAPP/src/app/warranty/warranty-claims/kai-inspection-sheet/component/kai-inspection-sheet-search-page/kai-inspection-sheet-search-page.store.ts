import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class KisSearchPageStore {
    private readonly searchForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.searchForm = this.fb.group({
            wcrNo: [{value: null, disabled: false}],
            inspectionNo: [{value: null, disabled: false}],
            fromDate: [{value: null, disabled: false}],
            toDate: [{value: null, disabled: false}],
        });
    }

   /*  createForm() {
        this.searchForm = this.fb.group({
            wcrNo: [{value: null, disabled: false}],
            wcrType: [{value: null, disabled: false}],
            wcrStatus: [{value: null, disabled: false}],
            pcrNo: [{value: null, disabled: false}],
            jobCardNo: [{value: null, disabled: false}],
            chassisNo: [{value: null, disabled: false}],
            wcrFromDate: [{value: null, disabled: false}],
            wcrToDate: [{value: null, disabled: false}],
            machineModel: [{value: null, disabled: false}],
            failureType: [{value: null, disabled: false}],
            mobileNo: [{value: null, disabled: false}],
            registrationNo: [{value: null, disabled: false}],
            jobCardFromDate: [{value: null, disabled: false}],
            jobCardToDate: [{value: null, disabled: false}],
            pcrFromDate: [{value: null, disabled: false}],
            pcrToDate: [{value: null, disabled: false}],
        });
    } */

    get kisSearchForm() {
        if(this.searchForm) {
            return this.searchForm as FormGroup;
        }
        /* this.createForm();
        return this.searchForm as FormGroup; */
    }
}