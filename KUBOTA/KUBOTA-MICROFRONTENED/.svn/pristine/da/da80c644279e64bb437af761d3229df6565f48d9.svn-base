import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ServiceActivityReportSearchStore } from './service-activity-report-search-page.store';

@Injectable()
export class ServiceActivityReportSearchPresenter {

    readonly serviceActivityReportSearchForm: FormGroup

    constructor(
        private serviceActivityReportSearchStore: ServiceActivityReportSearchStore
    ) {
        this.serviceActivityReportSearchForm = this.serviceActivityReportSearchStore.serviceActivityReportSearchForm
    }

    get detailsForm() {
        return this.serviceActivityReportSearchForm.get('serviceActivityReportSearchDetailsForm') as FormGroup
    }

}