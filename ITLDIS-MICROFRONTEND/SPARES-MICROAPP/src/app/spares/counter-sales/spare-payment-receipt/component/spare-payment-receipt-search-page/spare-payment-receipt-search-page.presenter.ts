import { Injectable } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { SparePaymentReceiptSearchPageStore } from './spare-payment-receipt-search-page.store';

@Injectable()
export class SparePaymentReceiptSearchPagePresenter {
    private readonly sparePaymentReceiptSearchPageStore = new SparePaymentReceiptSearchPageStore(this.fb);

    constructor(private fb: FormBuilder) { }

    get getSearchPaymentReceiptForm() {
        return this.sparePaymentReceiptSearchPageStore.getSearchPaymentReceiptForm;
    }
}