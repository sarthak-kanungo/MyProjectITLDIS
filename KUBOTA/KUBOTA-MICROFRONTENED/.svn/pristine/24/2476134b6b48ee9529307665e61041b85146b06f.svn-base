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
            claimNumber: [null],
            activityType: [null],
            activityNumber: [null],
            claimStatus: [null],
            fromDate: [null],
            toDate: [null],
            state: [null],
            orgHierarchyId: [null],
            dealerCode: [null],
            page: [null],
            size: [null]
        })
    }
}