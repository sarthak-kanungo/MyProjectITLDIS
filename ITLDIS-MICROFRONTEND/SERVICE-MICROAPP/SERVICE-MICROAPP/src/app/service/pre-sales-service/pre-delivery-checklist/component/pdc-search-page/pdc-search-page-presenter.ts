import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PdcSearchPageStore } from './pdc-search-page.store';

@Injectable()
export class PdcSearchPagePresenter {

    readonly pdcSearchForm: FormGroup

    constructor(
        private pdcSearchPageStore: PdcSearchPageStore
    ) {
        this.pdcSearchForm = this.pdcSearchPageStore.pdcSearchForm
    }

    get detailsForm() {
        return this.pdcSearchForm.get('pdcSearchDetailsForm') as FormGroup
    }

}