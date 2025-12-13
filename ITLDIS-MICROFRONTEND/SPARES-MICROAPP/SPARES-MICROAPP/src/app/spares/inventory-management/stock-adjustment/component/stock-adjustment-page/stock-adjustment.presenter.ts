import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { StockAdjustmentStore } from './stock-adjustment.store';



@Injectable()
export class StockAdjustmentPresenter {

    readonly stockAdjustmentForm: FormGroup;
    private _operation: string;

    set operation(type: string) {
        this._operation = type;
    }
    get operation() {
        return this._operation;
    }
    constructor(
        private stockAdjustmentStore: StockAdjustmentStore
    ) {
        this.stockAdjustmentForm = this.stockAdjustmentStore.stckAdjustmentFormGroup;
    }
    markFormAsTouched() {
        this.stockAdjustmentForm.markAllAsTouched();
    }


    get inventoryAdjustmentDetailsForm() {
        return this.stockAdjustmentForm.get('inventoryAdjustmentDetailsForm') as FormGroup
    }

    get itemDetailsTable() {
        return this.stockAdjustmentForm.get('itemDetailsTable') as FormGroup;
    }
    createItemDetailsTableRow(data?) {
        return this.stockAdjustmentStore.createItemDetailsTableRow(data)
    }
    addRow(data?) {
        let itemDetails = this.itemDetailsTable.get('itemDetailsRowData') as FormArray
        let fg:FormGroup 
        if(itemDetails.valid || itemDetails.length==0){
            let fg:FormGroup = this.createItemDetailsTableRow(data);
            itemDetails.push(fg);
            return fg;
        }else{
            this.itemDetailsTable.markAllAsTouched();
        }
       return null;     
    }
    deleteRow() {
        let itemDetails = this.itemDetailsTable.get('itemDetailsRowData') as FormArray;
        let nonSelected = itemDetails.controls.filter(element => !element.value.isSelected)
        itemDetails.clear()
        nonSelected.forEach(el => itemDetails.push(el))
        if(itemDetails.length==0){
            this.addRow()
        }
    }
    
    resetFormByItemNo(fg: FormGroup){
        fg.controls.itemNo.enable();
        fg.controls.itemDescription.reset();
        fg.controls.currentStock.reset();
        fg.controls.store.reset();
        fg.controls.binLocation.reset();
        fg.controls.transferQty.reset();
        fg.controls.mrp.reset();
        fg.controls.adjustmentType.reset();
    }
}