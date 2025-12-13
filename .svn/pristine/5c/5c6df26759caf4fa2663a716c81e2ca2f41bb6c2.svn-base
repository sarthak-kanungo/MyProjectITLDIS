import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { ModelSurveyMasterCreatePageStore } from "./model-survey-master-create-page.store";

@Injectable()
export class ModelSurveyMasterCreatePagePresenter {
    readonly createModelSurveyMasterForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(private pageStore:ModelSurveyMasterCreatePageStore){
        this.createModelSurveyMasterForm= pageStore.modelSurveyForm
    }
    public get modelServeyFormArray() {
        return this.createModelSurveyMasterForm.get('modelServeyFormArray') as FormArray
    }
    
    addRowFromData(row?:any){
        const fg = this.pageStore.buildmodelServeyFormArray();
        if(row){
            fg.patchValue(row);
        }
        this.modelServeyFormArray.push(fg);
    }

    addRowForManualEntry(){
        const fg = this.pageStore.buildmodelServeyFormArray();
        this.modelServeyFormArray.push(fg);
    }

    removeRow(index:number){
        this.modelServeyFormArray.removeAt(index)
    }

}