import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PartQuotationSearchPageStore } from './part-quotation-search-page.store';


@Injectable()
export class PartQuotationSearchPagePresenter {

    readonly partQuotationSearchForm: FormGroup

    constructor(
        private partQuotationSearchPageStore: PartQuotationSearchPageStore
    ) {
        this.partQuotationSearchForm = this.partQuotationSearchPageStore.partQuotationSearchForm
    }

    get detailsForm() {
        return this.partQuotationSearchForm.get('partQuotationSearchDetailsForm') as FormGroup
    }

}