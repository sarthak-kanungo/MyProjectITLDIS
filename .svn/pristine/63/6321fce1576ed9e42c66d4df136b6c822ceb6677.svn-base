import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { ComplaintCreatePageStore } from "./complaint-page.store";

@Injectable()
export class ComplaintCreatePagePresenter {
    readonly createComplaintForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(private pageStore: ComplaintCreatePageStore) {
        this.createComplaintForm = pageStore.complaintForm
    }
    public get complaintFormArray() {
        return this.createComplaintForm.get('complaintFormArray') as FormArray
    }
    public get complaintFormGroup() {
        return this.createComplaintForm.get('additionalComment') as FormGroup
    }

    addRowFromData(row?: any) {
        const fg = this.pageStore.buildcomplaintFormArray();
        if (row) {
            fg.patchValue(row);
        }
        this.complaintFormArray.push(fg);
    }

    addRowForManualEntry() {
        const fg = this.pageStore.buildcomplaintFormArray();
        this.complaintFormArray.push(fg);
    }

    removeRow(index: number) {
        this.complaintFormArray.removeAt(index)
    }

}