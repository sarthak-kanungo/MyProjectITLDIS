import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { branchTransferIssueStore } from "./branch-transfer-issue-store";

@Injectable()
export class branchTransferIssuePresenter {
    private _operation: string;
    branchTransferIssueForm:FormGroup
    constructor(
       private issueStore:branchTransferIssueStore,
    ) { }

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    get issueForm(): FormGroup {
        return this.issueStore.branchTransferIssueForm.get('issueForm') as FormGroup;
    }
    
    // for item Details
    get addItems(): FormArray {
        let itemDetails = this.issueStore.orderForm.get('items') as FormArray;
        if (itemDetails.valid) {
            itemDetails.push(this.issueStore.initializeItemDetailsForm());
        }
        return itemDetails as FormArray;
    }
    // For Add Row
    addRowInImplement(){
        const implementControl = this.issueStore.orderForm.get('items') as FormArray;
         const newForm = this.issueStore.initializeItemDetailsForm();
         implementControl.push(newForm);
    }


    
}
