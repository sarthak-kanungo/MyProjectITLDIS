import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormSearchPageStore } from './form-search-page.store';

@Injectable()
export class StoreSearchPagePresenter {
    readonly storeSearchForm: FormGroup

    constructor(
        private formSearchPageStore: FormSearchPageStore
    ) {
        this.storeSearchForm = this.formSearchPageStore.searchStoreForm
    }

    get searchForm() {
        return this.storeSearchForm.get('storeSearchForm') as FormGroup
    }
}