import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()

export class ServiceActivityClaimSearchPageStore {

    private readonly _serviceActivityClaimSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._serviceActivityClaimSearchForm = this.formBuilder.group({
            serviceActivityClaimSearchDetailsForm: this.buildServiceActivityClaimSearchForm(),
        })
    }

    get serviceActivityClaimSearchForm() {
        return this._serviceActivityClaimSearchForm
    }

    private buildServiceActivityClaimSearchForm() {
        return this.formBuilder.group({
            activityClaimNumber: [null],
            activityClaimStatus: [null],
            activityNumber: [null],
            fromDate: [null],
            toDate: [null],
            activityType: [null],
            activityEffectiveness: [null],
            orgHierarchyId: [null],
            dealerCode: [null],

        })
    }
    
}