import { FormGroup, FormBuilder } from '@angular/forms';

export class PartReturnSearchPageStore {
    private readonly _partReturnPageForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._partReturnPageForm = this.createPartReturnPageForm();
    }
    
    public get partReturnPageForm() : FormGroup {
        return this._partReturnPageForm;
    }
    
    createPartReturnPageForm() {
        return this.fb.group({
            partReturnNo: null,
            requisitionNo: null,
            jobCardNo: null,
            requisitionType: null,
            requisitionPurpose: null,
            reasonForReturn: null,
            requisitionFromDate: null,
            requisitionToDate: null,
            issueFromDate: null,
            issueToDate: null,
            page: [0],
            size: [10]
        });
    }
}