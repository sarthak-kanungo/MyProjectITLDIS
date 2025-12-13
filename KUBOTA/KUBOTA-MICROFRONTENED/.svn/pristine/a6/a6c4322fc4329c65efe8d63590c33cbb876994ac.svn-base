import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, FormArray,Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class BtBtPageStore {


    private readonly _btBtFormGroup: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._btBtFormGroup = this.fb.group({
            btBtDetailsForm: this.buildingBtBtDetailsForm(),
            itemDetailsTable: this.fb.group({
                itemDetailsRowData: this.fb.array([])
            }),
        });
    }
    get btBtFormGroup() {
        return this._btBtFormGroup;
    }

    private buildingBtBtDetailsForm(): FormGroup {
        return this.fb.group({
            binTransferNo: [{ value: null, disabled: false }],
            binTransferDate: [{ value: null, disabled: true }],
        })
    }

    createItemDetailsTableRow(data?): FormGroup {
        let fg =  this.fb.group({
            rowId: [null],
            id: [null],
            itemNo: [{value: null, disabled: false},Validators.required],
            itemDescription: [{ value: null, disabled: true},Validators.required],
            availableStock : [{value: null, disabled: true},Validators.required],
            fromStore: [{value: null, disabled: false},Validators.required],
            fromLocation: [{value: null, disabled: false},Validators.required],
            fromBinStock: [{value: null, disabled: true},Validators.required],
            transferQty: [{value : null, disabled: false},Validators.required],
            toStore: [{value: null, disabled: false},Validators.required],
            toLocation: [{value: null, disabled: false},Validators.required],
            //toLocation: [{value: null, disabled: false},Validators.compose([Validators.pattern('^[A-Z][0-9][0-9]-[0-9][0-9]-[a-zA-Z0-9]+')])],
            toBinStock: [{value: null,disabled:true},Validators.required],
            isSelected: [null]
        });
        
        //fg.controls.transferQty.setValidators(Validators.compose([Validators.required, CustomValidators.max(fg.controls.fromBinStock,'Tranfer Quantity should not be more than Stock')]));
        /* comment  by vinay, bcoz  above line is executed on load and also show arror msg */
        if(data){
            fg.patchValue(data);
            fg.controls.transferQty.setValidators(Validators.compose([Validators.required,CustomValidators.max(fg.controls.fromBinStock,'Tranfer Quantity should not be more than Stock')]));
        }
        
        return fg;
    }
}