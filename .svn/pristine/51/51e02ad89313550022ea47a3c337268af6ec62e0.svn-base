import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { ComplaintCreatePageStore } from "./complaint-page.store";

@Injectable()
export class ComplaintCreatePagePresenter {
    readonly createModelSurveyMasterForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(private pageStore:ComplaintCreatePageStore){
        this.createModelSurveyMasterForm= pageStore.complaintForm
    }
    public get complaintFormArray() {
        return this.createModelSurveyMasterForm.get('complaintFormArray') as FormArray
    }
    
    addRowFromData(row?:any){
        const fg = this.pageStore.buildcomplaintFormArray();
        if(row){
            fg.patchValue(row);
        }
        this.complaintFormArray.push(fg);
    }

    addRowForManualEntry(){
        const fg = this.pageStore.buildcomplaintFormArray();
        console.log(fg)
        this.complaintFormArray.push(fg);
    }

    removeRow(index:number){
        this.complaintFormArray.removeAt(index)
    }

}