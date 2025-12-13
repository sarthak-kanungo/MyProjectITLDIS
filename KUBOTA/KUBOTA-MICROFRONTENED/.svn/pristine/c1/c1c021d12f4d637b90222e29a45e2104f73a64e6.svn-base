import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { BtBtPageStore } from './btbt-page.store';


@Injectable()
export class BtBtPagePresenter {

    readonly btBtForm: FormGroup;
    private _operation: string;

    set operation(type: string) {
        this._operation = type;
    }
    get operation() {
        return this._operation;
    }
    constructor(
        private btBtPageStore: BtBtPageStore
    ) {
        this.btBtForm = this.btBtPageStore.btBtFormGroup;
    }
    markFormAsTouched() {
        this.btBtForm.markAllAsTouched();
    }

    get btBtDetailsForm() {
        return this.btBtForm.get('btBtDetailsForm') as FormGroup
    }

    get itemDetailsTable() {
        return this.btBtForm.get('itemDetailsTable') as FormGroup;
    }
    createItemDetailsTableRow(data?) {
        return this.btBtPageStore.createItemDetailsTableRow(data)
    }
    addRow(data, isExcel?:boolean) {
        let itemDetails = this.itemDetailsTable.get('itemDetailsRowData') as FormArray
        
        if(itemDetails.length == 0  || (itemDetails.controls[itemDetails.length-1] as FormGroup).controls.itemNo.value){
            let fg = this.createItemDetailsTableRow(data) as FormGroup;
            if(isExcel){
                fg.controls.itemNo.disable();
            }
            itemDetails.push(fg);
            return fg;
        }/*else if(isExcel){
            let fg = itemDetails.controls[itemDetails.length-1] as FormGroup;
            //debugger;
            fg.patchValue(data);
            fg.controls.fromLocation.patchValue(data.fromLocation);
            fg.controls.fromStore.patchValue(data.fromStore);
            fg.controls.toBinStock.patchValue(data.toBinStock);
            fg.controls.transferQty.patchValue(data.transferQty);
            fg.controls.fromBinStock.patchValue(data.fromBinStock);
            fg.controls.toLocation.patchValue(data.toLocation);
            fg.controls.toStore.patchValue(data.toStore);
            fg.controls.itemNo.disable();
            return fg;
        }*/
    }
    deleteRow() {
        let itemDetails = this.itemDetailsTable.get('itemDetailsRowData') as FormArray;
        let nonSelected = itemDetails.controls.filter(element => !element.value.isSelected)
        itemDetails.clear()
        nonSelected.forEach(el => itemDetails.push(el))
    }

    resetFormByItemNo(fg: FormGroup){
        fg.controls.itemNo.enable();
        fg.controls.itemDescription.reset();
        fg.controls.availableStock.reset();
        fg.controls.fromStore.reset();
        fg.controls.fromLocation.reset();
        fg.controls.fromBinStock.reset();
        fg.controls.transferQty.reset();
        fg.controls.toStore.reset();
        fg.controls.toLocation.reset();
        fg.controls.toBinStock.reset();
    }
}