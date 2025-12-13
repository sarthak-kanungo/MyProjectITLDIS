import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class DealerDepartmentMasterPageStore {

    private readonly _dealerDepartmentMasterForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._dealerDepartmentMasterForm = this.formBuilder.group({

            dealerMasterForm: this.formBuilder.group({
                dealerDepartmentMaster: this.dealerMasterForm()
            }),

        })
    }

    get customerSearchForm() {
        return this._dealerDepartmentMasterForm
    }

    dealerMasterForm() {
        return this.formBuilder.group({
            departmentCode: [null, Validators.required],
            departmentName: [null, Validators.required],
            remarks: [null],
            isSelected: [null],
        })
    }
}