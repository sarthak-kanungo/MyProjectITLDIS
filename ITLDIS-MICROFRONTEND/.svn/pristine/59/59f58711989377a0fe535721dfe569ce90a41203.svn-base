import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class PscSearchPageStore {

    private _pscSearchFormgroup: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._pscSearchFormgroup = this.formBuilder.group({
            pscSearchForm: this.buildPscSearchForm()
        })
    }

    get searchPscForm() {
        return this._pscSearchFormgroup
    }

    private buildPscSearchForm() {
        return this.formBuilder.group({
            chassisNo: [null],
            pscNo: [null],
            fromDate: [null],
            toDate: [null],
            orgHierarchyId:[null]
        })
    }

}