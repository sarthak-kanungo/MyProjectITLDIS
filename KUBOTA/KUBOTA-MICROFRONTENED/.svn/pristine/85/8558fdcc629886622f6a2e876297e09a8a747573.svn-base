import { ChangeDetectorRef, Injectable } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import * as uuid from 'uuid';

@Injectable()
export class PickListPageStore {
    private readonly _pickListFormGroup: FormGroup;

	constructor(
		private fb: FormBuilder
	) {
        this._pickListFormGroup = this.fb.group({
			pickListForm: this.buildingPickListForm(),
            itemDetails: this.fb.array([])
        });
    }

    get pickListFormGroup() {
		return this._pickListFormGroup;
	}
    private buildingPickListForm(): FormGroup {
        return this.fb.group({
            id:[null],
            saleOrderNumber: [{ value: null, disabled: false }, Validators.required],
            customerName: [{ value: null, disabled: true }],
            contactNumber: [{ value: null, disabled: true }],
            city: [{ value: null, disabled: true }],
            picklistNumber: [{ value: null, disabled: true }],
            picklistDate: [{ value: null, disabled: true }],
            saleOrderDate: [{ value: null, disabled: true }],
        })
    }
    private buildingItemPartPopUp(): FormGroup {
		return this.fb.group({
			itemNumber: [''],
		});
	}

    createItemDetailsTableRow(data?): FormGroup {
		let itemControls = this.fb.group({
		    uuid: [uuid.v4()],
			id: [null],
			isSelected : [false],
			sparePartDtlId:[null],
			itemNumber: [{ value:null, disabled: true }, Validators.required],
            itemDescription: [{ value:null, disabled: true }, Validators.required],
            orderQuantity: [{ value:null, disabled: true }, Validators.required],
            balanceQuantity: [{ value:null, disabled: true }],
            currentStock: [{ value:null, disabled: true }, Validators.required],
            issueQuantity: [{ value:null, disabled: true }, Validators.required],
            returnQuantity: [{ value:null, disabled: false }],
            returnedQuantity: [{ value:null, disabled: true }],
            finalIssueQuantity: [{ value:null, disabled: true }],
            store: [{ value:null, disabled: true }, Validators.required],
            binLocation: [{ value:null, disabled: true }, Validators.required],
            mrp: [{ value:null, disabled: true }, Validators.required],
            binId: [null],
            storeId : [null],
            spmgst : [null],
            spegst : [null],
            spmrp : [null],
            unitPrice : [null]
        });
		if(data){
            console.log(data)
		    itemControls.patchValue(data);
            console.log(itemControls.controls.unitPrice.value)
		}
		return itemControls;
	}
		

}