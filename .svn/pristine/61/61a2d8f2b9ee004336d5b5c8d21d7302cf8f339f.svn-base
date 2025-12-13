import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { SparesSalesInvoiceSearchPageStore } from './spares-sales-invoice-search-page.store';

@Injectable()
export class SparesSalesInvoiceSearchPagePresenter {
    private readonly sparesSalesInvoiceSearchPageStore = new SparesSalesInvoiceSearchPageStore(this.fb);

    constructor(
        private fb: FormBuilder
    ) { }

    public get searchInvoiceForm(): FormGroup {
        return this.sparesSalesInvoiceSearchPageStore.searchSparesInvoiceForm;
    }

}
