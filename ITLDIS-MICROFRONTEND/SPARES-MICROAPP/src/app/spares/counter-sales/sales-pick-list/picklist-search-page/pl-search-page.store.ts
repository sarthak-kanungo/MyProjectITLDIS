import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";

@Injectable()
export class PlSearchPageStore {
    private readonly _plSearchForm: FormGroup

    constructor(
        private fb: FormBuilder) {
        this._plSearchForm = this.fb.group({
            pLSearch: this.buildingPlSearchForm()
        })
    }

    get plSearchFormGroup() {
        return this._plSearchForm
    }

    private buildingPlSearchForm(): FormGroup {
        return this.fb.group({
            picklistNumber:'',
            customerOrderStatus: [null],
            orderFromDate: [null],
            orderToDate: [null],
        })
    }

}