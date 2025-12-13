import { Injectable } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { DealerDepartmentMasterPageStore } from './dealer-department-master-page.store';


@Injectable()
export class DealerDepartmentMasterPagePresenter {

    readonly createDealerDepartmentMasterForm: FormGroup

    private _operations: string

    set operation(type: string) {
        this._operations = type
    }
    get operation() {
        return this._operations
    }

    constructor(
        private dealerDepartmentMasterPageStore: DealerDepartmentMasterPageStore
    ) {
        this.createDealerDepartmentMasterForm = this.dealerDepartmentMasterPageStore.customerSearchForm
    }

    get dealerMasterForm() {
        return this.createDealerDepartmentMasterForm.get('dealerMasterForm') as FormGroup
    }

    get dealerMasterCreateForm() {
        return this.dealerMasterForm.get('dealerDepartmentMaster') as FormGroup
    }
    markFormAsTouched() {
        this.createDealerDepartmentMasterForm.markAllAsTouched();
    }
}