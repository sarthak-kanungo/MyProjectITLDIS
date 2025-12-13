import { FormGroup, FormBuilder } from '@angular/forms';

export class PartIssueSearchPageStore {
    private readonly _partIssuePageForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._partIssuePageForm = this.createPartIssuePageForm();
    }
    
    public get partIssuePageForm() : FormGroup {
        return this._partIssuePageForm;
    }
    
    createPartIssuePageForm() {
        return this.fb.group({
            partIssueNo: null,
            requisitionNo: null,
            jobCardNo: null,
            requisitionPurpose: null,
            requisitionFromDate: null,
            requisitionToDate: null,
            issueFromDate: null,
            issueToDate: null,
            page: [0],
            size: [10],
            tableColumn:null
        });
    }
}