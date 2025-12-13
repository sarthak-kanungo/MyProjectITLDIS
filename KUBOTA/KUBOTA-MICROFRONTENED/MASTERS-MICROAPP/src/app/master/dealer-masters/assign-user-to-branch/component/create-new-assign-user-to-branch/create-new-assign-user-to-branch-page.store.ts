import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";

@Injectable()
export class CreateNewAssignUserToBranchPageStore {

    private readonly _userToBranchForm: FormGroup;
    
    constructor(private formBuilder: FormBuilder){
        this._userToBranchForm= formBuilder.group({
            userToBranchForm:this.buildUserToBranchForm(),
            branchAccessible : new FormArray([])
            // branchAccessible:this.buildbranchAccessible(),
            // abtu: this.formBuilder.array([]),
            // tableData: this.formBuilder.group({
            //     table: this.formBuilder.array([
            //     ])
            // }),
        })
    }
    
    public get userToBranchForm(){
        return this._userToBranchForm
    }
    
    private buildUserToBranchForm(){
        return this.formBuilder.group({
            dealership: [, Validators.compose([])],
            userId: [{ value: "", disabled: true }, Validators.compose([Validators.required])],
        })
    }

    // public buildbranchAccessible(){
    //     return this.formBuilder.group({
    //         branch_name: [{value:'', }],
    //         isactive: [{value:'', }],
    //         createdBy: [{ value: '', disabled: true }],
    //     })
    // }

    public buildbranchAccessible(){
        return this.formBuilder.group({
            Active_status: [{value:'', }],
            IsmainBranch: [{value:'', disabled: true }],
            branch_code: [{value:'', }],
            branch_name: [{value:'', }],
            id: [{ value: '', }],
        })
    }
}