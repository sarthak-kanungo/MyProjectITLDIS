import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SparePaymentReceiptPageStore } from './spare-payment-receipt-page.store';
import { AddPaymentReceiptData } from 'SparePaymentReceiptModule';

@Injectable()
export class SparePaymentReceiptPagePresenter {
    private readonly sparePaymentReceiptPageStore = new SparePaymentReceiptPageStore(this.fb);

    constructor(private fb: FormBuilder) { }

    get receiptForm(): FormGroup {
        return this.sparePaymentReceiptPageStore.getCreatePaymentReceiptForm.get('receiptDetails') as FormGroup;
    }
    get payeeDetailForm(): FormGroup {
        return this.sparePaymentReceiptPageStore.getCreatePaymentReceiptForm.get('payeeDetails') as FormGroup;
    }
    get paymentDetailForm(): FormGroup {
        return this.sparePaymentReceiptPageStore.getCreatePaymentReceiptForm.get('paymentDetails') as FormGroup;
    }
    public resetForm() {
        this.sparePaymentReceiptPageStore.getCreatePaymentReceiptForm.reset();
    }
    public markAllAsTouched() {
        this.sparePaymentReceiptPageStore.getCreatePaymentReceiptForm.markAllAsTouched();
    }
    public isFormValid() {
        return this.sparePaymentReceiptPageStore.getCreatePaymentReceiptForm.valid;
    }
    get getFormValues() {
        return this.sparePaymentReceiptPageStore.getCreatePaymentReceiptForm.getRawValue();
    }
    public disableMainForm() {
        this.sparePaymentReceiptPageStore.getCreatePaymentReceiptForm.disable();
    }
    public patchValueToMainForm(formValues: object) {
        this.receiptForm.patchValue(formValues);
        this.paymentDetailForm.patchValue(formValues);
        this.payeeDetailForm.patchValue(formValues);
    }
}