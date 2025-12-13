import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SapSearchPageStore } from './sap-search-page.store';

@Injectable()
export class SapSearchPagePresenter {

    readonly sapSearchForm: FormGroup

    constructor(
        private sapSearchPageStore: SapSearchPageStore
    ) {
        this.sapSearchForm = this.sapSearchPageStore.sapSearchForm
    }

    get detailsForm() {
        return this.sapSearchForm.get('sapSearchDetailsForm') as FormGroup
    }

}