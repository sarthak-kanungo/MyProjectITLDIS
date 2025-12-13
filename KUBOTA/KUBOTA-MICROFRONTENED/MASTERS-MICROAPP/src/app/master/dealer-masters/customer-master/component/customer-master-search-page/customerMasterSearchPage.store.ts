import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Injectable()
export class CustomerMasterSearchPageStore {

    private readonly _customerMasterSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._customerMasterSearchForm = this.formBuilder.group({
            customerSearchDetailsForm: this.searchCustomerFormDetails(),

        })
    }

    get customerSearchForm() {
        return this._customerMasterSearchForm
    }

    private searchCustomerFormDetails() {
        return this.formBuilder.group({

            customerCode: ['', Validators.compose([])],
            mobileNo: ['', Validators.compose([])],
        })
    }
}