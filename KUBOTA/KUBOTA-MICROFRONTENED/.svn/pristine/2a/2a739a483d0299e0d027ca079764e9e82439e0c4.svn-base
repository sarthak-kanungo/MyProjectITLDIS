import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { CustomerMasterSearchPageStore } from './customerMasterSearchPage.store';

@Injectable()
export class CustomerMasterSearchPagePresenter {

    readonly searchCustomerMasterForm: FormGroup

    constructor(
        private customerMasterSearchPageStore: CustomerMasterSearchPageStore
    ) {
        this.searchCustomerMasterForm = this.customerMasterSearchPageStore.customerSearchForm
    }

    get customerSearchDetails() {
        return this.searchCustomerMasterForm.get('customerSearchDetailsForm') as FormGroup
    }

}