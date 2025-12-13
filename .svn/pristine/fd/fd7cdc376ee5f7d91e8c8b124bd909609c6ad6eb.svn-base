import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class WcrSearchPageStore {
    private readonly searchForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.searchForm = this.fb.group({
            wcrNo: [{value: null, disabled: false}],
            wcrType: [{value: null, disabled: false}],
            wcrStatus: [{value: null, disabled: false}],
            pcrNo: [{value: null, disabled: false}],
            jobCardNo: [{value: null, disabled: false}],
            chassisNo: [{value: null, disabled: false}],
            wcrFromDate: [{value: null, disabled: false}],
            wcrToDate: [{value: null, disabled: false}],
            model: [{value: null, disabled: false}],
            failureType: [{value: null, disabled: false}],
            mobileNo: [{value: null, disabled: false}],
            registrationNo: [{value: null, disabled: false}],
            jobCardFromDate: [{value: null, disabled: false}],
            jobCardToDate: [{value: null, disabled: false}],
            pcrFromDate: [{value: null, disabled: false}],
            pcrToDate: [{value: null, disabled: false}],
            page: [0],
            size: [10],
            state:[null],
            branch:[null],
            dealerShip:[null],
            orgHierarchyId: [{value: null, disabled: false}],
            finalStatus:[{value:null,disabled:false}]
        });
    }

    /* createForm() {
        this.searchForm = this.fb.group({
            wcrNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            wcrType: [{value: null, disabled: false}],
            wcrStatus: [{value: null, disabled: false}],
            pcrNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            jobCardNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            chassisNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            wcrFromDate: [{value: null, disabled: false}],
            wcrToDate: [{value: null, disabled: false}],
            model: [{value: null, disabled: false}],
            failureType: [{value: null, disabled: false}],
            mobileNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            registrationNo: [{value: null, disabled: false}],
            jobCardFromDate: [{value: null, disabled: false}],
            jobCardToDate: [{value: null, disabled: false}],
            pcrFromDate: [{value: null, disabled: false}],
            pcrToDate: [{value: null, disabled: false}],
            page: [0],
            size: [10]
        });
    } */

    get wcrSearchForm() {
        if(this.searchForm) {
            return this.searchForm as FormGroup;
        }
        /* this.createForm();
        return this.searchForm as FormGroup; */
    }
}