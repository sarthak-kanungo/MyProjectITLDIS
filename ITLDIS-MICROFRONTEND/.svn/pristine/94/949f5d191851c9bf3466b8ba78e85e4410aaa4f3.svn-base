import { FormGroup, FormBuilder } from '@angular/forms'
import { Injectable } from '@angular/core';
@Injectable()
export class MrcSearchPageStore {

    private readonly _mrcSearchForm: FormGroup

    constructor(
        private fb: FormBuilder
    ) {
        this._mrcSearchForm = this.fb.group({
            mrcSearch: this.buildingMrcSearchForm()
        })
    }
    get mrcSearchFormGroup() {
        return this._mrcSearchForm
    }
    private buildingMrcSearchForm(): FormGroup {
        return this.fb.group({
            mrcNo: [''],
            kaiInvoiceNo: [''],
            chassisNo: [''],
            mrcDateFrom: [''],
            mrcDateTo: [''],
            invoiceDateFrom: [''],
            invoiceDateTo: [''],
            orgHierarchyId:[null]
        })
    }

}

