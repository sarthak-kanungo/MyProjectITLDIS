import { Injectable,ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';

@Injectable()
export class PartQuotationPageStore {

    private readonly _partQuotationForm: FormGroup

    constructor(
        private _changeDetectorRef: ChangeDetectorRef, 
        private formBuilder: FormBuilder
    ) {
        this._partQuotationForm = this.formBuilder.group({
            partQuotationDetailsForm: this.buildPartQuotationForm(),
            itemDetails:  this.createPartQuotationTableRow(),
            partQuotationTotal: this.createPartQuotationTotal()
        })
    }

    get partQuotationForm() {
        return this._partQuotationForm
    }

    private buildPartQuotationForm() {
        return this.formBuilder.group({
            quotationNumber: [{ value: null, disabled: true }],
            quotationDate: [{ value: null, disabled: true }],
            customerType: [null, Validators.compose([Validators.required])],
            retailer: [null, Validators.compose([])],
            retailerName: [null, Validators.compose([])],
            mechanic: [null, Validators.compose([])],
            mechanicName: [null, Validators.compose([])],
            mobileNumber: [null, Validators.compose([Validators.required])],
            customerName: [null, Validators.compose([])],
            prospectMasterId: [null],
            customerMasterId: [null],
            partyMasterId: [null],
            address1: [null, Validators.compose([Validators.required])],
            address2: [null],
            country: [{ value: null, disabled: true }],
            state: [null, Validators.compose([Validators.required])],
            district: [null, Validators.compose([Validators.required])],
            tehsil: [null, Validators.compose([Validators.required])],
            city: [null, Validators.compose([Validators.required])],
            pinCode: [null, Validators.compose([Validators.required])],
            postOffice: [null, Validators.compose([Validators.required])],
            discountRate: [null, Validators.compose([this.exceedsAssetValue.bind(this)])],
            totalDiscountValue: [{ value: 0, disabled: true }],
            totalBasicValue: [{ value: 0, disabled: true }],
            totalTaxValue: [{ value: 0, disabled: true }],
            totalQuotationAmount: [{ value: 0, disabled: true }],
        })
    }

    createPartQuotationTableRow() {
        return {
            itemNo: [{ value: null, disabled: false },Validators.compose([Validators.required])],
            itemDescription: [{ value: null, disabled: true }],
            hsnCode: [{ value: null, disabled: true }],
            unitPrice: [{ value: null, disabled: true }],
            quantity: [{ value: null, disabled: false },Validators.compose([Validators.required])],
            sparePartMasterId: [null],
            amount: [{ value: null, disabled: true }],
            discountPercent: [{ value: null, disabled: false },this.exceedsAssetValue.bind(this)],
            discountAmount: [{ value: null, disabled: true }],
            netAmount: [{ value: null, disabled: true }],
            cgstPercent: [{ value: null, disabled: true }],
            cgstAmount: [{ value: null, disabled: true }],
            sgstPercent: [{ value: null, disabled: true }],
            sgstAmount: [{ value: null, disabled: true }],
            igstPercent: [{ value: null, disabled: true }],
            igstAmount: [{ value: null, disabled: true }],
            isSelected: [false],
            id:[null],
            total: [{ value: 0, disabled: true }],
        }
    }

    private createPartQuotationTotal() {
        return this.formBuilder.group({
            subTotal: [{ value: 0, disabled: true }, Validators.compose([Validators.required])],
            gstAmount: [{ value: 0, disabled: true }, Validators.compose([Validators.required])],
            totalInvoiceAmount: [{ value: 0, disabled: true }, Validators.compose([Validators.required])],
        })
    }

    private exceedsAssetValue(control: AbstractControl) {
        if (control && control.value) {
          let assetValue = 100
          if (parseFloat(control.value) > assetValue) {
            return { 'moreThan': true }
          }
          return 0.0
        }
      }

}