import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class ReInstallationSearchPageStore {

    private readonly _reInstallationSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._reInstallationSearchForm = this.formBuilder.group({
            reInstallationSearchDetailsForm: this.buildReInstallationSearchForm(),
        })
    }

    get reInstallationSearchForm() {
        return this._reInstallationSearchForm
    }

    private buildReInstallationSearchForm() {
        return this.formBuilder.group({
            series: [null],
            serviceStaffName: [null],
            reInstallationNo: [null],
            fromDate: [null],
            toDate: [null],
        })
    }

}