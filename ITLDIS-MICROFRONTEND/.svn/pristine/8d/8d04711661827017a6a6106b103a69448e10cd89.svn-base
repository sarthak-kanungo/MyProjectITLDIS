import { Injectable } from "@angular/core";
import { PartIssueSearchPageStore } from './part-issue-search-page.store';
import { FormBuilder, FormGroup } from '@angular/forms';

@Injectable()
export class PartIssueSearchPagePresenter {
    private readonly partIssueSearchPageStore: PartIssueSearchPageStore;
    constructor(fb: FormBuilder){
        this.partIssueSearchPageStore = new PartIssueSearchPageStore(fb);
    }
    
    public get partIssueSearchForm() : FormGroup {
        return this.partIssueSearchPageStore.partIssuePageForm;
    }
    
}