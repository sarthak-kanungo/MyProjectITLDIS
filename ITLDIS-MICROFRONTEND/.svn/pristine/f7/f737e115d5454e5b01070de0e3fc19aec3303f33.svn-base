import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormCreatePageStore } from './form-create-page.store';


@Injectable()
export class StoreCreatePagePresenter {

    readonly storeCreateForm: FormGroup

    constructor(
        private formCreatePageStore: FormCreatePageStore
    ) {
        this.storeCreateForm = this.formCreatePageStore.createStoreForm
    }

    get storeForm() {
        return this.storeCreateForm.get('storeCreateForm') as FormGroup
    }
}