import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomValidators } from "src/app/utils/custom-validators";
import { Operation } from "src/app/utils/operation-util";
import { issuePresenter } from "./branch-transfer-presenter";
@Injectable()
export class issueStore {
    private readonly branchTransferIssueForm: FormGroup;
    isEdit:any
    private _operation: string;
    set operation(type: string) {
        this._operation = type
       
    
    }
    get operation() {
        return this._operation
    }

    constructor(
        private fb: FormBuilder,
        
    ) {
        this.branchTransferIssueForm = this.fb.group({
            formIssue: this.createIssueForm(),
            branchIssueItem: this.fb.array([]),
        }); 
    }



    private createIssueForm() {
        const formIssue = this.fb.group({
            branchTransferIssueNo:[{value:'',disabled:true}],
            reqNo: [{ value: null, disabled: true },Validators.required],
            issueDate: [{ value: '', disabled: true }],
            
            byBranchMaster: [{ value: '', disabled: true }],
            employeeMaster: [{ value: '', disabled: true }],
            toBranchMaster: [{ value: '', disabled: false }, Validators.required],
            employeeId:[{value:'',disabled:false}],
            byBranchMasterId:[{value:'',disabled:false}],
            toBranchId:[{value:null,disabled:true}],
            status:[{value:null,disabled:true}],
            id:[{value:null,disabled:true}],
        });

        return formIssue;
    }
    createItemDetailTableRow(data?) {
        
        let fg:FormGroup = this.fb.group({
            
            itemNo: [{ value: data?data.sparePartMaster.itemNo: null, disabled: true}],
            itemDescription: [{ value: data?data.sparePartMaster.itemDescription:null, disabled: true }],
            currentStockQty: [{ value: data?data.currentStockQty:null, disabled: true }],
            reqQty: [{ value: data?data.reqQty:null, disabled: true }],
            sparePartMaster: [{ value: data ? { id: data.sparePartMaster.id, itemNo: data.sparePartMaster.itemNo } : null, disabled: true }],
            transferIndent:[{ value: data ? { id: data.transferIndent.id} : null, disabled: true }], 
            issuedQty: [{ value: data?data.issuedQty:null, disabled: false },Validators.compose([Validators.required,CustomValidators.numberOnly])],
            pendingQty: [{ value: data?data.pendingQty:null, disabled: true }],
            // storeMaster: data ? data.storeMaster.id : null,
            //  storeMaster: [{value: data ? { id: data.storeMaster.id, store: data.storeMaster.storeName } : null, disabled: true }],
            binLocationMaster: [{value:data ? {id:data.id,value:data.binLocation} : null, disabled: false },Validators.compose([Validators.required, CustomValidators.selectFromLists()])],
            storeMaster: [{ value: data ? data.store : null, disabled: false  }],
             id: [{ value: data == undefined ? null : data.id, disabled: false }],
            itemMrp: [{value: data?data.itemMrp:null, disabled: true}],
            itemValue:[{value: data?data.itemValue:null, disabled: true}],
            totalValue:[{value:data?data.totalValue:null,disabled:true}]
        })
          
        
        return fg;
    }

     public get issueFormAll() {
        if (this.branchTransferIssueForm) {
         
            return this.branchTransferIssueForm as FormGroup;
        }
    }

    public get partReturnTableControl(): FormArray {
    
        return this.branchTransferIssueForm.get('branchIssueItem') as FormArray;
    }
   
}