import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PscSearchPageStore } from './psc-search-page.store';

@Injectable()
export class PscSearchPagePresenter {

    readonly pscSearchForm: FormGroup

    constructor(
        private pscSearchPageStore: PscSearchPageStore
    ) {
        this.pscSearchForm = this.pscSearchPageStore.searchPscForm
    }

    get searchForm() {
        return this.pscSearchForm.get('pscSearchForm') as FormGroup
    }
}