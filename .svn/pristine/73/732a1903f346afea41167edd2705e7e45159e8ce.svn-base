import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { SubmitItemDetail } from '../../domain/stock-adjustment.domain';

@Injectable()
export class StockAdjustmentStore {


    private readonly _stockAdjustmentFormGroup: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._stockAdjustmentFormGroup = this.fb.group({
            inventoryAdjustmentDetailsForm: this.buildingInventoryAdjustmentDetailsForm(),
            itemDetailsTable: this.fb.group({
                itemDetailsRowData: this.fb.array([])
            }),
        });
    }
    get stckAdjustmentFormGroup() {
        return this._stockAdjustmentFormGroup;
    }

    private buildingInventoryAdjustmentDetailsForm(): FormGroup {
        return this.fb.group({
            adjustmentNumber: [{ value: null, disabled: true }],
            adjustmentDate: [{ value: null, disabled: true }],
            adjustmentStatus: [{ value: null, disabled: true }]
        })
    }

    createItemDetailsTableRow(data?:SubmitItemDetail): FormGroup {
        return this.fb.group({
            rowId: [data ? data.rowId : null],
            id: [data ? data.id : null],

            itemNo: [data ? data.partNo : null, Validators.required],
            itemDescription: [{ value: data ? data.description : null, disabled: true }, Validators.required],
            store: [{value:data ? data.store : null, disabled: true }, Validators.required],
            binLocation: [{value:data ? {id:data.stockBinId,value:data.binLocation} : null, disabled: true }, Validators.required],
            currentStock: [{value:null, disabled: true }],
            transferQty: [{value:data ? data.qtyAdjusted : null, disabled: true }, Validators.compose([Validators.required,CustomValidators.numberOnly, Validators.min(1), Validators.max(1000)])],
            adjustmentType: [{value:data ? (data.adjustmentType=='I'?'Increase':'Decrease') : null, disabled: true }, Validators.required],
            mrp: [{value:data ? data.mrp : null, disabled: true }, Validators.compose([Validators.required,CustomValidators.numberOnly, Validators.min(1)])],
            isSelected: [null],

        });
    }
}