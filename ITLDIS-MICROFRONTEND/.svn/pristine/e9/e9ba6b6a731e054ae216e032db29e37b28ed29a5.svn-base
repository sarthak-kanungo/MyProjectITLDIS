import { Injectable } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { DealerDesignationMasterPageStore } from './dealer-designation-master-page.store';


@Injectable()
export class DealerDesignationMasterPagePresenter {

    readonly createDealerDesignationMasterForm: FormGroup

    private _operations: string

    set operation(type: string) {
        this._operations = type
    }
    get operation() {
        return this._operations
    }

    constructor(
        private dealerDesignationMasterPageStore: DealerDesignationMasterPageStore
    ) {
        this.createDealerDesignationMasterForm = this.dealerDesignationMasterPageStore.customerSearchForm
    }

    get dealerMasterForm() {
        return this.createDealerDesignationMasterForm.get('dealerMasterForm') as FormGroup
    }

    get dealerMasterTableForm() {
        return this.dealerMasterForm.get('dealerDesignationMasterForm') as FormGroup
    }

    markFormAsTouched() {
        this.createDealerDesignationMasterForm.markAllAsTouched();
    }

}