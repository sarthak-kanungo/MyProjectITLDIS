import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomValidators } from "src/app/utils/custom-validators";


@Injectable()
export class backOrderStore {
    private readonly backOrderCancellationForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.backOrderCancellationForm = this.fb.group({
            backOrderForm: this.createBackOrderForm(),
            itemDetails: this.fb.array([]),
        });
    }



    private createBackOrderForm() {
        const backOrderForm = this.fb.group({
            dealerCode:[{value:null,disabled:true}],
            dealerName: [{ value: null, disabled: true }],
            reqGivenBy: [{ value: null, disabled: true }],
            mobileNumber: [{ value: '', disabled: true }], 
            employeeMasterId:[{value:null,disabled:true}],
            bocNo:[{value:null,disabled:true}]
        });
        return backOrderForm;
    }
    createItemDetailTableRow(data?) {
      
        let fg:FormGroup = this.fb.group({
            itemNo: [{ value: data?data.sparePartMaster.itemNo: null, disabled: true}],
            purchaseOrder:[{ value: data ? { id: data.purchaseOrder.id, purchaseOrderNumber: data.purchaseOrder.purchaseOrderNumber } : null, disabled: true }],
             purchaseOrderNumber:[{value: data?data.purchaseOrder.purchaseOrderNumber: null, disabled: true}],
            itemDescription: [{ value: data?data.sparePartMaster.itemDescription:null, disabled: true }],
            accpacPoNo: [{ value: data?data.accpacPoNo:null, disabled: true }],
            sparePartMaster: [{ value: data ? { id: data.sparePartMaster.id, itemNo: data.sparePartMaster.itemNo } : null, disabled: true }],
            pendingOrderQty: [{ value: data?data.pendingOrderQty:null, disabled: true }],
            kaiAcceptedQty: [{ value: data?data.kaiAcceptedQty:null, disabled: false },Validators.required],
             id: [{ value: data == undefined ? null : data.id, disabled: false }],
             cancelQty: [{value: data?data.cancelQty:null, disabled: true}],
            //  "status": "Pending for Approval",
             status:["Pending for Approval"]

        })
          
        
        return fg;
    }



    get backOrderFormAll() {
        if (this.backOrderCancellationForm) {
            return this.backOrderCancellationForm as FormGroup;
        }
    }


    public get partReturnTableControl(): FormArray {
        return this.backOrderCancellationForm.get('itemDetails') as FormArray;
    }
    
}