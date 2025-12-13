import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Injectable()
export class SearchAssignUserToBranchPageStore {
    
    private readonly _assignUserToBranchForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {
        this._assignUserToBranchForm = this.formBuilder.group({
            userToBranchForm:this.buildAssignUserToBranchForm(),
            
        });
    }

    get searchAssignUserToBranchForm() {
        return this._assignUserToBranchForm;
    }

   private buildAssignUserToBranchForm(){
        return this.formBuilder.group({
           // userId: ['', Validators.compose([])],
            userId: [{ value: "", disabled: true }, Validators.compose([])],
            //dealership: ['', Validators.compose([])],
            dealership: [null, Validators.compose([])],
        })
    }
}