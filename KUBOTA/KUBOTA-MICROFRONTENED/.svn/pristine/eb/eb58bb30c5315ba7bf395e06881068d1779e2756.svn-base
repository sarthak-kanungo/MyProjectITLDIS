import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class InstallationSearchPageStore {

    private readonly _installationSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._installationSearchForm = this.formBuilder.group({
            installationSearchDetailsForm: this.buildInstallationSearchForm(),
        })
    }

    get installationSearchForm() {
        return this._installationSearchForm
    }

    private buildInstallationSearchForm() {
        return this.formBuilder.group({
            chassisNo: [null],
            installationNo: [null],
            installationType: [null],
            fromDate: [null],
            toDate: [null],
            dealerShip:[null]
        })
    }

}