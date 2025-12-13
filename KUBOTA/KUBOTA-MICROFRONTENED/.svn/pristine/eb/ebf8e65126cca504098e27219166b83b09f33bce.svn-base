import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class PdcSearchPageStore {

    private readonly _pdcSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._pdcSearchForm = this.formBuilder.group({
            pdcSearchDetailsForm: this.buildPdcSearchForm(),
        })
    }

    get pdcSearchForm() {
        return this._pdcSearchForm
    }

    private buildPdcSearchForm() {
        return this.formBuilder.group({
            chassisNo: [null],
            pdcNo: [null],
            pdcFromDate: [null],
            pdcToDate: [null],
            orgHierarchyId: [null]
        })
    }

}