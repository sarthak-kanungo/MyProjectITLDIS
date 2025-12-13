import { FormGroup, FormBuilder } from '@angular/forms';

export class SparesSalesInvoiceSearchPageStore {
    private _searchSparesInvoiceForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) { }

    private createForm() {
        this._searchSparesInvoiceForm = this.fb.group({
            salesInvoice: [null],
            customerCode: [null],
            customerName: [null],
            referenceDocument: [null],
            salesType: [null],
            modeOfTransport: [null],
            transporter: [null],
            fromDate: [null],
            toDate: [null],
            wcrNo:[null],
            jobCardNumber:[null]
        })
    }

    get searchSparesInvoiceForm(): FormGroup {
        if (this._searchSparesInvoiceForm) {
            return this._searchSparesInvoiceForm as FormGroup;
        }
        this.createForm();
        return this._searchSparesInvoiceForm as FormGroup;
    }
}