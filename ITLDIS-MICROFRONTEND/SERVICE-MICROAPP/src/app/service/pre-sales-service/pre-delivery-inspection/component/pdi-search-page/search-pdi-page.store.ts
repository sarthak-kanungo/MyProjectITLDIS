import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class SearchPdiStore {
    private _pdiSearchFormGroup: FormGroup
    constructor(private formBuilder: FormBuilder) {
        this._pdiSearchFormGroup = this.formBuilder.group({
            basicPdiSearch: this.buildPdiSearchFormGroup(),
        })
    }
    get searchPdiForm() {
        return this._pdiSearchFormGroup
    }

    private buildPdiSearchFormGroup() {
        return this.formBuilder.group({
            chassisNo: [null],
            pdiDateFrom: [null],
            pdiDateTo: [null],
            kaiInvoiceNo: [null],
            dmsgrnNo: [null],
            dmsgrnFromDate: [null],
            dmsgrnToDate: [null],
            orgHierarchyId:[null]
        })
    }
}