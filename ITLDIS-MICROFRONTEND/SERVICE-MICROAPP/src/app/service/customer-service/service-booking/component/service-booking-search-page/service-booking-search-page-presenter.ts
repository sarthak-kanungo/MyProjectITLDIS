import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ServiceBookingSearchPageStore } from './service-booking-search-page.store';



@Injectable()
export class ServiceBookingSearchPagePresenter {

    readonly serviceBookingSearchForm: FormGroup

    constructor(
        private serviceBookingSearchPageStore: ServiceBookingSearchPageStore
    ) {
        this.serviceBookingSearchForm = this.serviceBookingSearchPageStore.serviceBookingSearchForm
    }

    get detailsForm() {
        return this.serviceBookingSearchForm.get('serviceBookingSearchDetailsForm') as FormGroup
    }

}