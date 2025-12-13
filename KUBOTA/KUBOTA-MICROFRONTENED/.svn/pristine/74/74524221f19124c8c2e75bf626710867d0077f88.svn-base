import { FormBuilder, FormGroup } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

export class SparePaymentReceiptSearchPageStore {
    private searchSparesPaymentReceiptForm: FormGroup;

    constructor(private fb: FormBuilder) { }

    private createSearchPaymentReceiptForm() {
        this.searchSparesPaymentReceiptForm = this.fb.group({
            receiptNumber: [null],
            receiptMode: [null],
            customerName: [null],
            customerContactNumber: [null, CustomValidators.mobileNumber],
            fromDate: [null],
            toDate: [null]
        })
    }
    get getSearchPaymentReceiptForm() {
        if (this.searchSparesPaymentReceiptForm) {
            return this.searchSparesPaymentReceiptForm;
        }
        this.createSearchPaymentReceiptForm();
        return this.searchSparesPaymentReceiptForm;
    }
}