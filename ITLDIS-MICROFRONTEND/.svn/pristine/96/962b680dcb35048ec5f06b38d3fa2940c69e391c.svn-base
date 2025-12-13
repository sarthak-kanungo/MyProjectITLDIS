import { SoPagePresenter } from './so-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { Input } from '@angular/core';
@Input()
export class Calculations {
    discountRate: number
    constructor(private presenter: SoPagePresenter) {
    }
    orderValueChange() {
        let customerDetails = this.presenter.customerOrderForm.getRawValue()
        this.discountRate = customerDetails.discountRate
        if (this.presenter.itemDetailsTableRow.get('itemDetailsDataTable')) {
            let tableData = this.presenter.itemDetailsTableRow.get('itemDetailsDataTable') as FormArray
            console.log("tableData for PopUp====>>>> ", tableData.controls);
            tableData.controls.forEach((element: FormGroup) => {
                console.log("element ", element);
                element.get('quantity').valueChanges.subscribe(result => {
                    console.log("result ", result);
                    this.quantityCalculation()
                })
            })
        }
        else {
            let tableData = this.presenter.itemDetailsTableRow.get('itemDetailsTableData') as FormArray
            console.log("tableData ", tableData);
            tableData.controls.forEach(element => {
                console.log("element ", element);
                element.get('quantity').valueChanges.subscribe(result => {
                    console.log("result ", result);
                    this.quantityCalculation()
                })
            })
        }
    }
    quantityCalculation() {
        if (this.presenter.itemDetailsTableRow.get('itemDetailsDataTable')) {
            let tableData = this.presenter.itemDetailsTableRow.get('itemDetailsDataTable') as FormArray
            console.log("tableData ", tableData);
            tableData.controls.forEach((element: FormGroup) => {
                element.get('amount').patchValue(element.get('quantity').value * element.get('unitPrice').value)
                element.get('discountPercent').patchValue(this.discountRate | 0)
                element.get('discountAmount').patchValue((element.get('amount').value * (this.discountRate / 100) | 0).toFixed(2))
                element.get('netAmount').patchValue(element.get('amount').value - element.get('discountAmount').value)
                element.get('cgstAmount').patchValue((element.get('netAmount').value * (element.get('cgstAmount').value ? element.get('cgstAmount').value / 100 : 0)).toFixed(2))
                element.get('sgstAmount').patchValue((element.get('netAmount').value * (element.get('sgstAmount').value ? element.get('sgstAmount').value / 100 : 0)).toFixed(2))
                element.get('igstAmount').patchValue((element.get('netAmount').value * (element.get('igstAmount').value ? element.get('igstAmount').value / 100 : 0)).toFixed(2))
            })
        } else {
            let tableData = this.presenter.itemDetailsTableRow.get('itemDetailsTableData') as FormArray
            console.log("tableData ", tableData);
            tableData.controls.forEach((element: FormGroup) => {
                element.get('amount').patchValue(element.get('quantity').value * element.get('unitPrice').value)
                element.get('discountPercent').patchValue(this.discountRate)
                element.get('discountAmount').patchValue((element.get('amount').value * (this.discountRate / 100)).toFixed(2))
                element.get('netAmount').patchValue(element.get('amount').value - element.get('discountAmount').value)
                element.get('cgstAmount').patchValue(element.get('netAmount').value * (element.get('cgstAmount').value ? element.get('cgstAmount').value / 100 : 0))
                element.get('sgstAmount').patchValue(element.get('netAmount').value * (element.get('sgstAmount').value ? element.get('sgstAmount').value / 100 : 0))
                element.get('igstAmount').patchValue(element.get('netAmount').value * (element.get('igstAmount').value ? element.get('igstAmount').value / 100 : 0))
            })
        }
        this.totalCalculationForSubTotal()
        this.totalCalculationForGstAmount()
        this.totalInvoiceAmount()
    }
    private totalCalculationForSubTotal() {
        let subtotal = 0
        let discountAmount = 0
        let tableData = this.presenter.itemDetailsTableRow.getRawValue()
        console.log(" this.presenter.itemDetailsTableRow.getRawValue() ", this.presenter.itemDetailsTableRow.getRawValue());
        if (tableData.itemDetailsDataTable) {
            tableData.itemDetailsDataTable.forEach(element => {
                console.log("element ", element);
                subtotal += (element.netAmount && parseFloat(element.netAmount)) || 0
                discountAmount += (element.discountAmount && parseFloat(element.discountAmount)) || 0
            });
            console.log("subtotal ", subtotal);
            this.presenter.partsTotalForm.get('subTotal').patchValue(subtotal)
            this.presenter.customerOrderForm.get('totalDiscountValue').patchValue(discountAmount.toFixed(2))
            this.presenter.customerOrderForm.get('totalBasicValue').patchValue(subtotal)
        }
    }
    private totalCalculationForGstAmount() {
        let tableData = this.presenter.itemDetailsTableRow.getRawValue()
        let gstAmount = 0
        tableData.itemDetailsDataTable.forEach(element => {
            let cgstAmount = (element.cgstAmount && parseFloat(element.cgstAmount))
            let sgstAmount = (element.sgstAmount && parseFloat(element.sgstAmount))
            let igstAmount = (element.igstAmount && parseFloat(element.igstAmount))
            gstAmount += (cgstAmount + sgstAmount + igstAmount) || 0
        });
        this.presenter.partsTotalForm.get('gstAmount').patchValue(gstAmount.toFixed(2));
        this.presenter.customerOrderForm.get('totalTaxValue').patchValue(gstAmount.toFixed(2))
    }
    private totalInvoiceAmount() {
        let value = this.presenter.partsTotalForm.getRawValue()
        let totalAmount = 0
        totalAmount = value.subTotal + (value.gstAmount && parseFloat(value.gstAmount))
        this.presenter.partsTotalForm.get('totalInvoiceAmount').patchValue(totalAmount.toFixed(2))
        this.presenter.customerOrderForm.get('totalQuotationAmount').patchValue(totalAmount.toFixed(2))
    }
}