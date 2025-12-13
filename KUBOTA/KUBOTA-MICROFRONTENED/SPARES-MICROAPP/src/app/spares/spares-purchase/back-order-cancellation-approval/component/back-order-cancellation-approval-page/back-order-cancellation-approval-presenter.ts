import { Injectable } from "@angular/core";
import { FormGroup, FormArray } from '@angular/forms';



import { Operation } from '../../../../../utils/operation-util';


import { backOrderStore } from "./back-order-cancellation-approval-store";
import { BehaviorSubject } from "rxjs";



@Injectable()
export class backOrderPresenter {
 public itemDetailsGroup: BehaviorSubject<Array<FormGroup>> = new BehaviorSubject<Array<FormGroup>>(null);
    public readonly itemDetailsArray: FormArray;
    public readonly backOrderFormAll: FormGroup;
    private _operation: string;
    loginUser: any;
   
    
    constructor(

        private store: backOrderStore,

    ) {
        this.itemDetailsArray = this.store.partReturnTableControl;
        this.backOrderFormAll = this.store.backOrderFormAll;
          
    }

    set operation(type: string) {
        this._operation = type
       
    }
    get operation() {
        return this._operation
    }

    get BOCForm(): FormGroup {
        return this.store.backOrderFormAll.get('backOrderForm') as FormGroup;
    }
    get createItemDetailsTableRowFn() {
        return this.store.createItemDetailTableRow;
    }

    addRow(data?) {
        let fg: FormGroup = this.store.createItemDetailTableRow(data)
        this.itemDetailsArray.push(fg)
         this.itemDetailsGroup.next(this.itemDetailsArray.controls as FormGroup[]);
         if (this._operation == Operation.VIEW) {
            this.itemDetailsArray .disable();
        }
         
    }

    calCulateQty(data?:any){
        let fg: FormGroup = this.store.createItemDetailTableRow(data);
        console.log(fg,'fg')
    }
   

}