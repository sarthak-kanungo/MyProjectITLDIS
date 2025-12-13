import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class AssignOrgHierarchyToDealerSearchPageStore {


    private readonly _searchAssignOrgHierarchyToDealerForm: FormGroup;
    constructor(private formBuilder: FormBuilder) {
        this._searchAssignOrgHierarchyToDealerForm = this.formBuilder.group({
            assignOrgHierarchyToDealerForm:this.buildAssignOrgHierarchyToDealerSearchForm(),
            
        });
    }

    get searchAssignOrgHierarchyToDealerForm() {
        return this._searchAssignOrgHierarchyToDealerForm;
    }

   private buildAssignOrgHierarchyToDealerSearchForm(){
        return this.formBuilder.group({
            dealerId: [null, Validators.compose([])],
            departmentId: [null, Validators.compose([])],
            // page: [null, Validators.compose([])],
            // size: [null, Validators.compose([])],
        })
    }
    
}