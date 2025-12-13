import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

export class SparePaymentReceiptPageStore {
    private sparesPaymentReceiptForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) { }

    private createForm() {
        this.sparesPaymentReceiptForm = this.fb.group({
            receiptDetails: this.createReceiptForm(),
            payeeDetails: this.createPayeeDetailsForm(),
            paymentDetails: this.createPaymentDetailsForm(),
        });
    }

    private createReceiptForm() {
        return this.fb.group({
            receiptNo: [{ value: null, disabled: true }],
            receiptDate: [{ value: null, disabled: true }],
            receiptType: [null, Validators.compose([Validators.required])],
            receiptMode: [null, Validators.compose([Validators.required])],
            receiptAmount: [null, Validators.compose([Validators.required, CustomValidators.numberOnly])],
            payeeType: [null, Validators.compose([Validators.required])],
            customerDealerMechanicCode: [null, Validators.compose([Validators.required])],
        })
    }
    private createPayeeDetailsForm() {
        return this.fb.group({
            // payeeCode: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
            customerName: [{ value: null, disabled: true }],
            contactNumber: [{ value: null, disabled: true }],
            customerAddress1: [{ value: null, disabled: true }],
            customerAddress2: [{ value: null, disabled: true }],
            postOffice: [{ value: null, disabled: true }],
            country: [{ value: null, disabled: true }],
            state: [{ value: null, disabled: true }],
            district: [{ value: null, disabled: true }],
            tehsil: [{ value: null, disabled: true }],
            village: [{ value: null, disabled: true }],
            pinCode: [{ value: null, disabled: true }],
            remarks: [null]
        })
    }
    private createPaymentDetailsForm() {
        return this.fb.group({
            chequeDdNo: [null],
            chequeDdDate: [null],
            chequeDdBank: [null],

            transactionNo: [null],
            transactionDate: [null],
            bank: [null],

            cardNo: [null],
            cardType: [null],
            cardName: [null],
            serviceProvides: [null],
        })
    }

    get getCreatePaymentReceiptForm(): FormGroup {
        if (this.sparesPaymentReceiptForm) {
            return this.sparesPaymentReceiptForm as FormGroup;
        }
        this.createForm();
        return this.sparesPaymentReceiptForm as FormGroup;
    }
}