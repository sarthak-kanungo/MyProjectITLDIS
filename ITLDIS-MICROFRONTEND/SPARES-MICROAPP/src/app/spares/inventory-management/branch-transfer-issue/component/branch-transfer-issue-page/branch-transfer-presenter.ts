import { Injectable } from "@angular/core";
import { FormGroup, FormArray } from '@angular/forms';
import { Operation } from '../../../../../utils/operation-util';
import { issueStore } from "./branch-transfer-store";
import { BehaviorSubject } from "rxjs";
import { ToastrService } from "ngx-toastr";




@Injectable()
export class issuePresenter {
    public itemDetailsGroup: BehaviorSubject<Array<FormGroup>> = new BehaviorSubject<Array<FormGroup>>(null);
    public readonly partReturnItemFormArray: FormArray;
    // public issueFormAll
    public readonly issueFormAll: FormGroup;
    private _operation: string;
    x:number=0;
    finalAmount:any;
    final:any
    isEdit:any;
    constructor(

        private store:issueStore,
        private toaster:ToastrService
    ) {

        this.partReturnItemFormArray = this.store.partReturnTableControl;
        this.issueFormAll = this.store.issueFormAll;
     }

    set operation(type: string) {
        this._operation = type
        console.log(this._operation,'operation')
    }
    get operation() {
        return this._operation
    }

    get issueForm(): FormGroup {
        return this.store.issueFormAll.get('formIssue') as FormGroup;
    }
    get createItemDetailsTableRowFn() {
        return this.store.createItemDetailTableRow;
    }

    addRow(data){
        let fg:FormGroup = this.store.createItemDetailTableRow(data)
            this.partReturnItemFormArray.push(fg)  
        this.itemDetailsGroup.next(this.partReturnItemFormArray.controls as FormGroup[]);
        if (this._operation == Operation.VIEW) {
            this.partReturnItemFormArray .disable();
        }

    }
    
    quantitycalculation(){
        if(this.partReturnItemFormArray.getRawValue()){
            let totSum: number[] = [];
            let i = 0;
        
        (this.partReturnItemFormArray.controls as FormGroup[]).forEach(fg => {
            let issueQuanty=fg.controls.issuedQty.value;
            let requestQuanty=fg.controls.reqQty.value;
            let pendingQuantity=requestQuanty-Number(issueQuanty)

            if( requestQuanty <issueQuanty ){
                this.toaster.error("Issue Qty Not Greater then Request Qty")
                fg.controls.pendingQty.reset()
                fg.controls.value.reset()
                return false
             }else{
                fg.controls.pendingQty.patchValue(pendingQuantity)
             }
          
             let issueQtys=fg.controls.issuedQty.value;
             let  mrp=fg.controls.itemMrp.value
             let finalValue=issueQtys*mrp
             
              fg.controls.itemValue.patchValue(finalValue)

              totSum[i] = finalValue;
              i++;
         });

         let sum = 0;
         for(let j = 0; j < totSum.length; j++){
            sum += totSum[j];
         }
       this.finalAmount =sum 
     
    }

   }

   
}