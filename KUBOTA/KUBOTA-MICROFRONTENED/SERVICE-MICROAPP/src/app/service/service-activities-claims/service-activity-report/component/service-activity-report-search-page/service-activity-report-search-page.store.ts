import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()

export class ServiceActivityReportSearchStore {

    private readonly _serviceActivityReportSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._serviceActivityReportSearchForm = this.formBuilder.group({
            serviceActivityReportSearchDetailsForm: this.buildServiceActivityReportSearchForm(),
        })
    }

    get serviceActivityReportSearchForm() {
        return this._serviceActivityReportSearchForm
    }

    private buildServiceActivityReportSearchForm() {
        return this.formBuilder.group({
            activityNumber: [null],
            activityStatus: [null],
            activityType: [null],
            activityDateTo: [null],
            activityDateFrom: [null],
            activityEffectiveness: [null],
            targetedproduct: [null],
            orgHierarchyId:[null]
        })
    }
    
}