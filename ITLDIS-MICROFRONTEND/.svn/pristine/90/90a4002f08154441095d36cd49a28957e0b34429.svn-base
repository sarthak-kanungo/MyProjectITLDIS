import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Injectable()
export class CurrentStockPageStore {

    private readonly _currentStockSearchFormGroup: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._currentStockSearchFormGroup = this.fb.group({
            currentStockSearch: this.buildingCurrentStockSearchForm(),

        });
    }
    get currentStockFormGroup() {
        return this._currentStockSearchFormGroup;
    }

    private buildingCurrentStockSearchForm(): FormGroup {
        return this.fb.group({
            itemNo: [null, Validators.compose([])],
        })
    }
}