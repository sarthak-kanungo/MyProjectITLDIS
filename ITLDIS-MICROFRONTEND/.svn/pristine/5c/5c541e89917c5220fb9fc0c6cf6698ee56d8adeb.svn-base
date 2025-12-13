import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';


@Injectable()
export class StockAdjustmentSearchStore {

    private readonly _stockAdjustmentSearchFormGroup: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._stockAdjustmentSearchFormGroup = this.fb.group({
            stockSearch: this.buildingStockAdjustmentSearchForm(),

        });
    }
    get stockAdjustmentFormGroup() {
        return this._stockAdjustmentSearchFormGroup;
    }

    private buildingStockAdjustmentSearchForm(): FormGroup {
        return this.fb.group({
            stockAdjustmentNo: [null],
            adjustmentStatus:[null],
            dealerName: [{ value: null, disabled: true }],
            dealerCode:[null],
            adjustmentFromDate: [null],
            adjustmentToDate: [null],
            orgHierLevel1 : [null],
            orgHierLevel2 : [null],
            orgHierLevel3 : [null],
            orgHierLevel4 : [null],
            orgHierLevel5 : [null],
            dealerId : [null],
            hierId: [null],
            page : [null],
            size: [null]
        })
    }
}