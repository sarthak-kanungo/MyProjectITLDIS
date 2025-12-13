import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class WpdcSearchPageStore {
    private readonly searchForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.searchForm = this.fb.group({
            dcNo: [{value: null, disabled: false}],
            wcrNo: [{value: null, disabled: false}],
            dcFromDate: [{value: null, disabled: false}],
            dcToDate: [{value: null, disabled: false}],
            machineModel: [{value: null, disabled: false}],
            wcrFromDate: [{value: null, disabled: false}],
            wcrToDate: [{value: null, disabled: false}],
            page: [0],
            size: [10]
        });
    }

    /* createForm() {
        this.searchForm = this.fb.group({
            dcNo: [{value: null, disabled: false}],
            wcrNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            dcFromDate: [{value: null, disabled: false}],
            dcToDate: [{value: null, disabled: false}],
            machineModel: [{value: null, disabled: false}],
            wcrFromDate: [{value: null, disabled: false}],
            wcrToDate: [{value: null, disabled: false}],
            page: [0],
            size: [10]
        });
    } */

    get wpdcSearchForm() {
        if(this.searchForm) {
            return this.searchForm as FormGroup;
        }
        /* this.createForm();
        return this.searchForm as FormGroup; */
    }
}