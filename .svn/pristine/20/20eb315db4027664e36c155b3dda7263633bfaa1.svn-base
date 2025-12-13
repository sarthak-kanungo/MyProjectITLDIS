import { Injectable } from "@angular/core"
import { FormGroup } from "@angular/forms"
import { SearchDelearCustomerCareExCallStore } from "./search-dealer-customer-care-ex-call-store"

@Injectable()
export class SearchDelearCustomerCareExCallPresenter {

    customerCareExeCallSearchForm: FormGroup

    constructor(
        private searchdelearCustomerCareExCallStore: SearchDelearCustomerCareExCallStore

    ) {
        this.customerCareExeCallSearchForm = this.searchdelearCustomerCareExCallStore.callSerchForm
    }

    get callSearchForm() {
        return this.customerCareExeCallSearchForm;
    }
}