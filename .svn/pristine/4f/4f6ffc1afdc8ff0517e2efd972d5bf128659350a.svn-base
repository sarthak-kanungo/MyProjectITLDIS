import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class RfcSearchPageStore{
    private readonly searchRfcForm: FormGroup;
    constructor(
        private fb: FormBuilder,
    ) {
        console.log('------RFC SEARCH FORM CREATED**----');
        this.searchRfcForm = this.fb.group({
            retrofitmentNumber: [{value: null, disabled: false}],
            retrofitmentDate: [{value: null, disabled: false}],
            campaignName: [{value: null, disabled: false}],
            fromDate: [{value: null, disabled: false}],
            toDate: [{value: null, disabled: false}], 
            status: [{value: null, disabled: false}],
        })
    }

   /*  private createForm() {
        console.log('------RFC SEARCH FORM CREATED**----');
        this.searchRfcForm = this.fb.group({
            retrofitmentNumber: [{value: null, disabled: false}],
            retrofitmentDate: [{value: null, disabled: false}],
            campaignName: [{value: null, disabled: false}],
            startDate: [{value: null, disabled: false}],
            endDate: [{value: null, disabled: false}], 
            status: [{value: null, disabled: false}],
        })
    } */

    get rfcSearchForm(): FormGroup {
        if (this.searchRfcForm) {
            return this.searchRfcForm as FormGroup;
        }
        /* this.createForm();
        return this.searchRfcForm as FormGroup; */
    }
}