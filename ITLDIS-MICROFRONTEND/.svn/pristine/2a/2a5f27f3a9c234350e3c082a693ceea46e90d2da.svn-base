import { Injectable } from "@angular/core";
import { FormGroup } from '@angular/forms';
import { claimInvoiceStore } from "./service-claim-invoice-store";

@Injectable()
export class claimInvoicePresenter {

    constructor(
        private store:claimInvoiceStore ,
    ) {}

    get claimSearchForms(): FormGroup {
        // console.log(this.store.claimSearchForm,'presenter')
        return this.store.claimSearchForm;
    }
}