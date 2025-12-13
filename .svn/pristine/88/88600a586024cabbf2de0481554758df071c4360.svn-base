
import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class LogSheetSearchPageStore {
    private readonly searchLogsheetForm: FormGroup;

    constructor(
        private fb: FormBuilder,
    ) {
        console.log('------LOGSHEET SEARCH FORM CREATED**----');
        this.searchLogsheetForm = this.fb.group({
            logsheetNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            logsheetType: [{value: null, disabled: false}],
            status: [{value: null, disabled: false}],
            logsheetFromDate: [{value: null, disabled: false}],
            logsheetToDate: [{value: null, disabled: false}],
            chassisNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            jobCardNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            machineModel: [{value: null, disabled: false}],
            failureType: [{value: null, disabled: false}],
            mobileNo: [{value: null, disabled: false}, CustomValidators.selectFromList() ],
            registrationNo: [{value: null, disabled: false}],
            jobCardFromDate: [{value: null, disabled: false}],
            jobCardToDate: [{value: null, disabled: false}],
            page: [0],
            size: [10]

            
        })
    }

    /* private createForm() {
        console.log('------LOGSHEET SEARCH FORM CREATED**----');
        this.searchLogsheetForm = this.fb.group({
            logsheetNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            logsheetType: [{value: null, disabled: false}],
            status: [{value: null, disabled: false}],
            logsheetFromDate: [{value: null, disabled: false}],
            logsheetToDate: [{value: null, disabled: false}],
            chassisNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            jobCardNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            machineModel: [{value: null, disabled: false}],
            failureType: [{value: null, disabled: false}],
            mobileNo: [{value: null, disabled: false}, CustomValidators.selectFromList() ],
            registrationNo: [{value: null, disabled: false}],
            jobCardFromDate: [{value: null, disabled: false}],
            jobCardToDate: [{value: null, disabled: false}],
            page: [0],
            size: [10]

            
        })
    } */

    get logsheetSearchForm(): FormGroup {
        if (this.searchLogsheetForm) {
            return this.searchLogsheetForm as FormGroup;
        }
        /* this.createForm();
        return this.searchLogsheetForm as FormGroup; */
    }
}
