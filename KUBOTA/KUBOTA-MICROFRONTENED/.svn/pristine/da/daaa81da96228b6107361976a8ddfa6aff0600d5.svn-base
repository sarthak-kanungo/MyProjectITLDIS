import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { SearchAssignUserToBranchPageStore } from "./search-assign-user-to-branch-page-store";

@Injectable()
export class SearchAssignUserToBranchPagePresenter {

    readonly searchAssignUserToBranchForm: FormGroup

    constructor(
        private pageStore: SearchAssignUserToBranchPageStore
    ) {
        this.searchAssignUserToBranchForm = this.pageStore.searchAssignUserToBranchForm
    }

    markFormAsTouched() {
        this.searchAssignUserToBranchForm.markAllAsTouched();
    }

    get userToBranchForm(){
        return this.searchAssignUserToBranchForm.get('userToBranchForm') as FormGroup
    }


}