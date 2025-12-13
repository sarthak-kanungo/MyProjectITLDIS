import { Injectable } from "@angular/core";
import { FormGroup, FormArray } from '@angular/forms';
import { Operation } from '../../../../../utils/operation-util';
import { indentStore } from "./branch-indent-store";
import { PartData, warrantyHotlinePartReport } from "../domain/domaim";


@Injectable()
export class indentPresenter {
    private _operation: string;
    constructor(
        private store:indentStore
    ) { }

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    get indentForm(): FormGroup {
        return this.store.indentFormAll.get('formIndent') as FormGroup;
    }
    get itemDetailsForm(): FormArray {
        return this.store.indentFormAll.get('itemDetails') as FormArray;
    }
    

    addRowInItemDetails(partDetail?: PartData,excelUpload?:boolean) {
       
         if( this._operation==Operation.CREATE && excelUpload){
            const controls=this.itemDetailsForm;
             if(excelUpload){
                  let j:number=controls.value.length;
                  if(controls.value[j-1].sparePartMaster===null || controls.value[j-1].sparePartMaster===''){
                    controls.removeAt(j-1)
                  }
             }
             const newForms = this.store.initializePartDetailForm(partDetail);
             controls.push(newForms);
          }else{
            const controls=this.itemDetailsForm;
            const newForms = this.store.initializePartDetailForm(partDetail);
             controls.push(newForms);
             if (this._operation == Operation.VIEW) {
                        newForms.disable();
                    }
          }
        
        

    
}

    deleteRowInItemDetails() {
        const deleteRow = this.store.indentFormAll.get('itemDetails').value.filter(val => val.isSelected);
        if ((this.store.indentFormAll.get('itemDetails').value.length - deleteRow.length) > 0) {
            const control = this.store.indentFormAll.get('itemDetails') as FormArray;
            const notSelected = control.controls.filter(ctrl => !ctrl.value.isSelected);
            control.clear();
            notSelected.forEach(elt => { control.push(elt) });
        }
    }

}