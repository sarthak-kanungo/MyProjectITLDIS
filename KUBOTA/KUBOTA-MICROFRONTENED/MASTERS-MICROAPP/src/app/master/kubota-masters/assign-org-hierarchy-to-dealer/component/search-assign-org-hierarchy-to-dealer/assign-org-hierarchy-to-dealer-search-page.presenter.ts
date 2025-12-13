import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { AssignOrgHierarchyToDealerSearchPageStore } from "./assign-org-hierarchy-to-dealer-search-page.store";


@Injectable()
export class AssignOrgHierarchyToDealerSearchPagePresenter {

    readonly assignOrgHierarchyToDealerSearchForm: FormGroup

    constructor(
        private assignOrgHierarchyToDealerSearchPageStore: AssignOrgHierarchyToDealerSearchPageStore
    ) {
        this.assignOrgHierarchyToDealerSearchForm = this.assignOrgHierarchyToDealerSearchPageStore.searchAssignOrgHierarchyToDealerForm
    }

    markFormAsTouched() {
        this.assignOrgHierarchyToDealerSearchForm.markAllAsTouched();
    }

    get assignOrgHierarchyToDealerForm(){
        return this.assignOrgHierarchyToDealerSearchForm.get('assignOrgHierarchyToDealerForm') as FormGroup
    }


}