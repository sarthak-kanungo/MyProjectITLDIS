import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class SapSearchPageStore {

    private readonly _sapSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._sapSearchForm = this.formBuilder.group({
            sapSearchDetailsForm: this.buildSapSearchForm(),
        })
    }

    get sapSearchForm() {
        return this._sapSearchForm
    }

    private buildSapSearchForm() {
        return this.formBuilder.group({
            activityNumber: [null],
            activityStatus: [null],
            activityType: [null],
            targetedProduct: [null],
            activityProposalToDate: [null],
            activityProposalFromDate: [null],
            orgHierarchyId: [null],
        })
    }

}