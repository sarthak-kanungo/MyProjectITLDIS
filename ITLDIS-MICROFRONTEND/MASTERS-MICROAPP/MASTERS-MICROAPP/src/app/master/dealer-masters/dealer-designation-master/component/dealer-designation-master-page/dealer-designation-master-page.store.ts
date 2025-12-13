import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class DealerDesignationMasterPageStore {

    private readonly _dealerDesignationMasterForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._dealerDesignationMasterForm = this.formBuilder.group({

            dealerMasterForm: this.formBuilder.group({
                dealerDesignationMasterForm: this.dealerMasterForm()
            }),

        })
    }

    get customerSearchForm() {
        return this._dealerDesignationMasterForm
    }

    dealerMasterForm() {
        return this.formBuilder.group({
            designationCode: [null, Validators.required],
            designationName: [null, Validators.required],
            department: [null,Validators.required],
            remarks: [null],
            isSelected: [null],
        })
    }
}