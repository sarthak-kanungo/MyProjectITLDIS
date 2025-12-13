import { Injectable } from "@angular/core";
import { FormGroup, FormArray } from '@angular/forms';

import { BehaviorSubject } from "rxjs";
import { OpsStore } from "./order-planning-sheet-store";
import { Operation } from "src/app/utils/operation-util";


@Injectable()
export class opsPresenter {
    public itemDetailsGroup: BehaviorSubject<Array<FormGroup>> = new BehaviorSubject<Array<FormGroup>>(null);
    public readonly itemDetailsArray: FormArray;
    public readonly OPSFormAll: FormGroup;
    totalOrderValueforView:any;
    // private _operation: string;
  operation: any;
    constructor(
       
        private store:OpsStore
    ) { 
        this. itemDetailsArray= this.store.partReturnTableControl;
        this.OPSFormAll=this.store.OPSFormAll;
        
    }

   

    get OpsForms(): FormGroup {
        return this.store.OPSFormAll.get('formOPS') as FormGroup;
    }
    get materialDetailsForm() {
        // console.log(this.store.OPSFormAll)
        return this.store.OPSFormAll.get('partDetails') as FormArray
      }
    get createItemDetailsTableRowFn() {
        return this.store.createItemDetailTableRow;
    }
    
    addRow(data){
       
        let fg:FormGroup = this.store.createItemDetailTableRow(data)
            this.itemDetailsArray.push(fg)  
        this.itemDetailsGroup.next(this.itemDetailsArray.controls as FormGroup[]);
        if (this.operation == Operation.VIEW) {
            this.itemDetailsArray .disable();
        }

        
        
    }
    calCulateTotalOrderValue(){
        let totalOrderValue:number=0;
        const partDetails =this.materialDetailsForm as FormArray;
      partDetails['controls'].forEach(element => {

        if(element.get('unitPrice').value!=null || element.get('unitPrice').value!='' && element.get('actualOrderQty').value!=null || element.get('actualOrderQty').value!=''){
            let qty= Number(element.get('actualOrderQty').value);
            let uniPrice=Number(element.get('unitPrice').value);
            let gstPercent=Number(element.get('gstPercent').value);
            let finalCalculation=(qty*uniPrice*(100+gstPercent))/100;
             if(isNaN(finalCalculation)){
             }else{
            element.get('orderValue').patchValue(Number(finalCalculation));
            totalOrderValue += finalCalculation;
            this.totalOrderValueforView=totalOrderValue.toFixed(2);
            // console.log(this.totalOrderValueforView,'totalOrderValuePresenter@@@@@@@@@')
             }
        }else{
        
        }
      });
    }



    
   

    
   
}