import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class DealerDesignationMasterSearchPageStore {

    private readonly _dealerDesignationMasterSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._dealerDesignationMasterSearchForm = this.formBuilder.group({
            dealerDesignationSearchDetailsForm: this.searchDealerDesignationFormDetails(),

        })
    }

    get dealerSearchForm() {
        return this._dealerDesignationMasterSearchForm
    }

    private searchDealerDesignationFormDetails() {
        // return null
        return this.formBuilder.group({

            designationName: [null, Validators.compose([])],
            department: [null]

        })
    }
}