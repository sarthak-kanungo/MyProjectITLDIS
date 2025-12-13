import { Injectable } from "@angular/core";
import { FormGroup, FormArray } from '@angular/forms';
import { Operation } from '../../../../../utils/operation-util';
import { recieptStore } from "./branch-transfer-reciept-store";
import { BehaviorSubject } from "rxjs";



@Injectable()
export class recieptPresenter {
    public itemDetailsGroup: BehaviorSubject<Array<FormGroup>> = new BehaviorSubject<Array<FormGroup>>(null);
    public readonly itemDetailsArray: FormArray;
    // public issueFormAll
    public readonly RecieptFormAll: FormGroup;
    private _operation: string;
    constructor(
        private store:recieptStore
    ) { 
        this. itemDetailsArray= this.store.partReturnTableControl;
         this.RecieptFormAll = this.store.RecieptFormAll;
        
    }

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    get recieptForm(): FormGroup {
        return this.store.RecieptFormAll.get('formReciept') as FormGroup;
    }
    get createItemDetailsTableRowFn() {
        return this.store.createItemDetailTableRow;
    }
    
    addRow(data){
       
        let fg:FormGroup = this.store.createItemDetailTableRow(data)
            this.itemDetailsArray.push(fg)  
        this.itemDetailsGroup.next(this.itemDetailsArray.controls as FormGroup[]);
        if (this._operation == Operation.VIEW) {
            this.itemDetailsArray .disable();
        }

        

    }

    
   

    
   
}