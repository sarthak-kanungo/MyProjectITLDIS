import { Injectable,ChangeDetectorRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class SoPageStore {
	private readonly _soFormGroup: FormGroup;

	constructor(
	    private _changeDetectorRef: ChangeDetectorRef,    
		private fb: FormBuilder
	) {
		this._soFormGroup = this.fb.group({
			customerOrderForm: this.buildingCustomerOrderForm(),
			itemDetailsForm: this.createItemDetailsTableRow(),
			partsTotal: this.createPartTotal(),
			itemDetailsTableData: this.fb.group({
				itemDetailsDataTable: this.fb.array([])
			})
		});
	}
	get soFormGroup() {
		return this._soFormGroup;
	}
	private buildingCustomerOrderForm(): FormGroup {
		return this.fb.group({
			customerType: [{ value: null, disabled: false }],
			customerOrderNo: [{ value: null, disabled: true }],
			customerOrderDate: [{ value: null, disabled: true }],
			customerOrderStatus: [{ value: null, disabled: true }],
			quotationNo: [{ value: null, disabled: false }],
			mobileNumber: [{ value: null, disabled: false }],
			customerName: [{ value: null, disabled: false }],
			customerAddress1: [{ value: null, disabled: false }],
			customerAddress2: [{ value: null, disabled: false }],
			country: [{ value: null, disabled: false }],
			state: [{ value: null, disabled: false }],
			district: [{ value: null, disabled: false }],
			tehsil: [{ value: null, disabled: false }],
			village: [{ value: null, disabled: false }],
			pinCode: [{ value: null, disabled: false }],
			pinId: [{ value: null, disabled: false }],
			postOffice: [{ value: null, disabled: false }],
			contactNo: [{ value: null, disabled: false }],
			discountType: [{ value: null, disabled: false }],
			discountRate: [{ value: null, disabled: false }, CustomValidators.numberOnly],
			totalDiscountValue: [{ value: 0, disabled: true }],
			totalBasicValue: [{ value: 0, disabled: true }],
			totalTaxValue: [{ value: 0, disabled: true }],
			totalQuotationAmount: [{ value: 0, disabled: true }],
			prospectMasterId: [null],
			customerMasterId: [null],
			quotationNoId: [null],
			partyMasterId: [null],
			coDealerId: [null],
			dealerCode: [{ value: null, disabled: false }],
			dealerName: [{ value: null, disabled: true }],

			retailer: [{ value: null, disabled: false }],
			retailerName: [{ value: null, disabled: true }],

			mechanic: [{ value: null, disabled: false }],
			mechanicName: [{ value: null, disabled: true }],
		})
	}

	private createPartTotal() {
		return this.fb.group({
			subTotal: [{ value: null, disabled: true },Validators.required],
			gstAmount: [{ value: null, disabled: true }],
			totalInvoiceAmount: [{ value: null, disabled: true },Validators.required],
		});
	}
	createItemDetailsTableRow(data?): FormGroup {
		let machineForm = this.fb.group({
			rowId: [data ? data.rowId : null],
			id: [data ? data.id : null],
			sparePartMasterId: [data ? data.sparePartMasterId : null],
			itemNo: [{ value: data ? data.itemNo : null, disabled: false }, [Validators.required, CustomValidators.selectFromAutocompleteList()]],
			itemDescription: [{ value: data ? data.itemDescription : null, disabled: true }],
			hsnCode: [{ value: data ? data.hsnCode : null, disabled: true }],
			availableStock: [{ value: data ? data.availableStock : null, disabled: true }],
			quantity: [{ value: data ? data.quantity : null, disabled: false }, [Validators.required, CustomValidators.numberOnly]],
			issueQuantity: [{ value: data ? data.issueQuantity : null, disabled: true}],
			backQuantity: [{ value: data ? data.backQuantity : null, disabled: true}],
			unitPrice: [{ value: data ? data.unitPrice : null, disabled: true }],
			amount: [{ value: data ? data.amount : null, disabled: true }],
			discountPercent: [{ value: data ? data.discountPercent : null, disabled: true }],
			discountAmount: [{ value: data ? data.discountAmount : null, disabled: true }],
			netAmount: [{ value: data ? data.netAmount : null, disabled: true }],
			cgstPercent: [{ value: data ? data.cgstPercent : null, disabled: true }],
			cgstAmount: [{ value: data ? data.cgstAmount : null, disabled: true }],
			sgstPercent: [{ value: data ? data.sgstPercent : null, disabled: true }],
			sgstAmount: [{ value: data ? data.sgstAmount : null, disabled: true }],
			igstPercent: [{ value: data ? data.igstPercent : null, disabled: true }],
			igstAmount: [{ value: data ? data.igstAmount : null, disabled: true }],
			isSelected: [false],
			remark: [{ value: data ? data.remark : null, disabled: true }],
		});
		/*if(data){
		    machineForm.controls.itemNo.patchValue({'itemNo':data.itemNo, 'id':data.sparePartMasterId});
		}*/
		return machineForm;
	}

}
