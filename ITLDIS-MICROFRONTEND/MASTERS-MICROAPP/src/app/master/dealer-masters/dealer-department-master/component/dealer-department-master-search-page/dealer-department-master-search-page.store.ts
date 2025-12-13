import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class DealerDepartmentMasterSearchPageStore {

    private readonly _dealerDepartmentMasterSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._dealerDepartmentMasterSearchForm = this.formBuilder.group({
            dealerDepartmentSearchDetailsForm: this.searchDealerDepartmentFormDetails(),

        })
    }

    get dealerSearchForm() {
        return this._dealerDepartmentMasterSearchForm
    }

    private searchDealerDepartmentFormDetails() {
        return this.formBuilder.group({

            departmentname: [null, Validators.compose([])],
            departmentCode: [null, Validators.compose([])],

        })
    }
}