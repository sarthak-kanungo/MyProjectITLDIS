

import { Injectable } from '@angular/core';

import { FormGroup } from '@angular/forms';
import { StockAdjustmentSearchStore } from './search-stock-adjustment.store';



@Injectable()
export class StockAdjustmentSearchPagePresenter {

    readonly stockAdjustmentSearchForm: FormGroup;

    constructor(
        private store: StockAdjustmentSearchStore
    ) {
        this.stockAdjustmentSearchForm = this.store.stockAdjustmentFormGroup;
    }
    get stockSearch() {
        return this.stockAdjustmentSearchForm.get('stockSearch') as FormGroup
    }
}