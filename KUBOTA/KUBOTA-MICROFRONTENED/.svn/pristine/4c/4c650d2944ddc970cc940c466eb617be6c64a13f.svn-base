import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ServiceActivityClaimSearchPageStore } from './service-activity-claim-search-page.store';

@Injectable()
export class ServiceActivityClaimSearchPagePresenter {

    readonly serviceActivityClaimSearchForm: FormGroup

    constructor(
        private serviceActivityClaimSearchPageStore: ServiceActivityClaimSearchPageStore
    ) {
        this.serviceActivityClaimSearchForm = this.serviceActivityClaimSearchPageStore.serviceActivityClaimSearchForm
    }

    get detailsForm() {
        return this.serviceActivityClaimSearchForm.get('serviceActivityClaimSearchDetailsForm') as FormGroup
    }

    
}