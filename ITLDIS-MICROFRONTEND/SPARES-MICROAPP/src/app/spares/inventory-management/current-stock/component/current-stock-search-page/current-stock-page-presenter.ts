

import { Injectable } from '@angular/core';

import { FormGroup } from '@angular/forms';
import { CurrentStockPageStore } from './current-stock-page-store';


@Injectable()
export class CurrentStockPagePresenter {

    readonly currentStockSearchForm: FormGroup;

    constructor(
        private currentStockPageStore: CurrentStockPageStore
    ) {
        this.currentStockSearchForm = this.currentStockPageStore.currentStockFormGroup;
    }
    get currentStockSearch() {
        return this.currentStockSearchForm.get('currentStockSearch') as FormGroup
    }
}