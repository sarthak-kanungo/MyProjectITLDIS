import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Injectable()
export class SoSearchPageStore {
    private readonly _soSearchForm: FormGroup

    constructor(
        private fb: FormBuilder
    ) {
        this._soSearchForm = this.fb.group({
            soSearch: this.buildingSoSearchForm()
        })
    }
    get soSearchFormGroup() {
        return this._soSearchForm
    }
    private buildingSoSearchForm(): FormGroup {
        return this.fb.group({
            customerOrderNo: [null],
            customerName: [null],
            customerType: [null],
            customerOrderStatus: [null],
            orderFromDate: [null],
            orderToDate: [null],
        })
    }

}

