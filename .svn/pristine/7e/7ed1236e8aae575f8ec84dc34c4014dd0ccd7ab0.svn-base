import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { ToastrService } from "ngx-toastr";

@Injectable()
export class recieptStore {
    private readonly branchTransferRecieptForm: FormGroup;

    constructor(
        private fb: FormBuilder,
        private toaster:ToastrService
    ) {
        this.branchTransferRecieptForm = this.fb.group({
            formReciept: this.createRecieptform(),
            itemDetails: this.fb.array([]),
        });
    }



    private createRecieptform() {
        const formReciept = this.fb.group({
            transferIssue: [{ value: null, disabled: false },Validators.compose([Validators.required])],
            recieptNo:[{value:null,disabled:true}],
            issueBranch: [{ value: '', disabled: true }],
            receivedBy: [{ value: '', disabled: true }],
            recieveBranch: [{ value: '', disabled: true }],
            recieptDate: [{ value: '', disabled: true }],
            receivingBranchId:[{value:null,disabled:true}],
            receivedById:[{value:null,disabled:true}],
            id:[],
            issuingBranchId:[{value:null,disabled:true}]
        });

        return formReciept;
    }

    createItemDetailTableRow(data?) {
        if( data.sparePartMaster===null){
            this.toaster.error('Part Details Not Available')
            return 
         }
        let fg:FormGroup = this.fb.group({
            itemNo: [{ value: data?data.sparePartMaster.itemNo: null, disabled: true}],
            itemDescription: [{ value: data?data.sparePartMaster.itemDescription:null, disabled: true }],
            receiptQty: [{ value: data?data.receiptQty:null, disabled: true }],
            sparePartMaster: [{ value: data ? { id: data.sparePartMaster.id, itemNo: data.sparePartMaster.itemNo } : null, disabled: true }],
            issuedQty: [{ value: data?data.issuedQty:null, disabled: true }],
            binLocationMaster: [],
            storeMaster: [{ value: data ? data.store : null, disabled: false  }],
             id: [{ value: data == undefined ? null : data.id, disabled: false }],
            itemMrp: [{value: data?data.itemMrp:null, disabled: true}],
           
            receiptMrpValue:[{value:data?data.receiptMrpValue:null,disabled:true}]
        })

        
          
        
        return fg;
    }

    public get partReturnTableControl(): FormArray {
        return this.branchTransferRecieptForm.get('itemDetails') as FormArray;
    }
    
   

    get RecieptFormAll() {
        if (this.branchTransferRecieptForm) {
            return this.branchTransferRecieptForm as FormGroup;
        }
    }

    // initializeCreateItemDetailsForm(addItemDetails?: itemDetails) {
    //     return this.createItemDetailsForm(addItemDetails);
    // }
   
}