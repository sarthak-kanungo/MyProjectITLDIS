import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class PartQuotationSearchPageStore {

    private readonly _partQuotationSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._partQuotationSearchForm = this.formBuilder.group({
            partQuotationSearchDetailsForm: this.buildPartQuotationSearchForm(),
        })
    }

    get partQuotationSearchForm() {
        return this._partQuotationSearchForm
    }

    private buildPartQuotationSearchForm() {
        return this.formBuilder.group({
            quotationNumber: [null],
            customerName: [null],
            customerType: [null],
            quotationFromDate: [null],
            quotationToDate: [null],
        })
    }

}