import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { ComplaintOrQueryResolutionCreatePageStore } from "./complaint-or-query-resolution-create-page.store";


@Injectable()
export class ComplaintOrQueryResolutionCreatePagePresenter {
    readonly createcomplaintOrQueryResolutionForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(private pageStore:ComplaintOrQueryResolutionCreatePageStore){
        this.createcomplaintOrQueryResolutionForm= pageStore.complaintOrQueryResolutionForm
    }
    public get complaintOrQueryResolutionFormArray() {
        return this.createcomplaintOrQueryResolutionForm.get('complaintOrQueryResolutionFormArray') as FormArray
    }
    
    addRowFromData(row?:any){
        const fg = this.pageStore.buildsearchcomplaintOrQueryResolutionFormArray();
        if(row){
            fg.patchValue(row);
        }
        this.complaintOrQueryResolutionFormArray.push(fg);
    }

    addRowForManualEntry(){
        const fg = this.pageStore.buildsearchcomplaintOrQueryResolutionFormArray();
        this.complaintOrQueryResolutionFormArray.push(fg);
    }

    removeRow(index:number){
        this.complaintOrQueryResolutionFormArray.removeAt(index)
    }

}