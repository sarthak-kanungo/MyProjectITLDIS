import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';


@Injectable()
export class BtBtSearchStore {

    private readonly _btBtSearchFormGroup: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._btBtSearchFormGroup = this.fb.group({
            btBtSearch: this.buildingBtbtSearchForm(),

        });
    }
    get btBtFormGroup() {
        return this._btBtSearchFormGroup;
    }

    private buildingBtbtSearchForm(): FormGroup {
        return this.fb.group({
            binTransferNo: [null],
            binTransferFromDate: [null],
            binTransferToDate: [null],
        })
    }
}