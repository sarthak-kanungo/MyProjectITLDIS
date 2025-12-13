import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";

@Injectable()
export class ComplaintCreatePageStore {
    private readonly _complaintForm: FormGroup;
    constructor(private fb: FormBuilder) {
        this._complaintForm = fb.group({
            complaintFormArray: new FormArray([]),
            additionalComment: this.additionalComment()
        })
    }

    public get complaintForm() {
        return this._complaintForm
    }

    public buildcomplaintFormArray() {
        return this.fb.group({
            complaintNo: [null, Validators.compose([Validators.required])],
            department: [''],
            description: [''],
            complaintType: [''],
            actionTaken: ['']
        })
    }
    additionalComment() {
        return this.fb.group({
            additionalComment: [],
            smstextoCustomer: []
        })
    }


}