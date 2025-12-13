import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class FormCreatePageStore {

    private readonly _storeCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._storeCreateForm = this.formBuilder.group({
            storeCreateForm: this.buildStoreCreateForm()
        })
    }

    get createStoreForm() {
        return this._storeCreateForm
    }

    private buildStoreCreateForm() {
        return this.formBuilder.group({
            Branch: [null],
            storeCode: [{value : null, disabled : true}],
            storeName: [null],
            active: [null],
        })
    }

}