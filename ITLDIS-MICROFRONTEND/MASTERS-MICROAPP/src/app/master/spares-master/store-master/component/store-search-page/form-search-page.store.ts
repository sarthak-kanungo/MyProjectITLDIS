import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class FormSearchPageStore {

    private readonly _storeSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._storeSearchForm = this.formBuilder.group({
            storeSearchForm: this.buildStoreSearchForm()
        })
    }

    get searchStoreForm() {
        return this._storeSearchForm
    }

    private buildStoreSearchForm() {
        return this.formBuilder.group({
            Branch: [null],
            storeName: [null],
        })
    }

}