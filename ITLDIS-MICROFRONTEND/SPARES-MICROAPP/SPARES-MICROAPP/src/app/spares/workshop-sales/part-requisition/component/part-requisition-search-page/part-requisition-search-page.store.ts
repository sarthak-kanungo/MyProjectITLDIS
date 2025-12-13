import { FormGroup, FormBuilder, Validators } from '@angular/forms';

export class PartRequisitionSearchPageStore {
    public readonly partRequisitionSearchForm: FormGroup;
    constructor(private fb: FormBuilder) {
        this.partRequisitionSearchForm = this.createForm();
    }
    private createForm() {
        if (this.partRequisitionSearchForm) {
            return this.partRequisitionSearchForm;
        }
        return this.fb.group({
            page: [0, Validators.compose([Validators.required])],
            size: [10, Validators.compose([Validators.required])],
            jobCardNo: [null],
            requisitionNo: [null],
            requisitionPurpose: [null],
			fromDate: null,
            toDate: null,
            tableColumn:['All']
        });
    }
}