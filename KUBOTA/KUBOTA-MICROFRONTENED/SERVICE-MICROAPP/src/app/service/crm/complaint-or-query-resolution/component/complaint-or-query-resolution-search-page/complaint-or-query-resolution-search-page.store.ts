import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Injectable()
export class ComplaintOrQueryResolutionSearchPageStore {
    private readonly _complaintOrQueryResolutionFormSearchForm: FormGroup;
    constructor(private fb: FormBuilder) {
   
        this._complaintOrQueryResolutionFormSearchForm= this.buildcomplaintOrQueryResolution()
        
    }

    public get getcomplaintOrQueryResolutionSearchForm() {
        return this._complaintOrQueryResolutionFormSearchForm
    }

    public buildcomplaintOrQueryResolution() {
        return this.fb.group({
            status: [null],
            typeofComplaint: [null],
            department: [null],
            fromDate: [null],
            toDate: [null],
        })
    }

}