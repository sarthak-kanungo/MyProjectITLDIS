import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { UserBranch } from "../../domain/assign-user-to-branch-domain";
import { CreateNewAssignUserToBranchPageStore } from "./create-new-assign-user-to-branch-page.store";

@Injectable()
export class CreateNewAssignUserToBranchPagePresenter {

    readonly newAssignUserToBranchForm: FormGroup
    userBranchList:Array<UserBranch>

    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor( private createNewAssignUserToBranchPageStore:CreateNewAssignUserToBranchPageStore){
        this.newAssignUserToBranchForm=createNewAssignUserToBranchPageStore.userToBranchForm
    }

    markFormAsTouched() {
        this.newAssignUserToBranchForm.markAllAsTouched();
    }
    
    public get userToBranchForm() {
        return this.newAssignUserToBranchForm.get('userToBranchForm') as FormGroup
    }

    public get branchAccessible() {
        return this.newAssignUserToBranchForm.get('branchAccessible') as FormArray
    }

    get tableData() {
        return this.createNewAssignUserToBranchPageStore.userToBranchForm.get('tableData') as FormGroup
    }
    get accessibleToUserForm() {
        return this.tableData.get('table') as FormArray
    }

    public get abtuForm(): FormArray {
        return this.createNewAssignUserToBranchPageStore.userToBranchForm.get('abtu') as FormArray;
    }
 
    
    addRow(element?:any){
        const fg = this.createNewAssignUserToBranchPageStore.buildbranchAccessible();
        if(element){
            fg.patchValue(element);
        }
        this.branchAccessible.push(fg);
    }
}