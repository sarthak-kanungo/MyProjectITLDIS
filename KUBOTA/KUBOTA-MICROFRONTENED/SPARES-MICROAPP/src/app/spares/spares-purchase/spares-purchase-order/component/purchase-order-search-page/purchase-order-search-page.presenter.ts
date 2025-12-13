import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PurchaseOrderSearchPageStore } from './purchase-order-search-page.store';

@Injectable()
export class PurchaseOrderSearchPagePresenter {

    constructor(
        private purchaseOrderSearchPageStore: PurchaseOrderSearchPageStore
    ) { }

    public get getSearchPOForm(): FormGroup {
        return this.purchaseOrderSearchPageStore.getSearchPoForm;
    }

}
