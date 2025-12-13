import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { ComplaintOrQueryResolutionSearchPageStore } from "./complaint-or-query-resolution-search-page.store";

@Injectable()
export class ComplaintOrQueryResolutionSearchPagePresenter{
    readonly searchComplaintOrQueryResolutionForm: FormGroup
    constructor(private pageStore:ComplaintOrQueryResolutionSearchPageStore){
        this.searchComplaintOrQueryResolutionForm= this.pageStore.getcomplaintOrQueryResolutionSearchForm
    }
    markFormAsTouched() {
        this.searchComplaintOrQueryResolutionForm.markAllAsTouched();
    }

    public get newcomplaintOrQueryResolutionFormSearchForm(){
        return this.searchComplaintOrQueryResolutionForm
    }
}