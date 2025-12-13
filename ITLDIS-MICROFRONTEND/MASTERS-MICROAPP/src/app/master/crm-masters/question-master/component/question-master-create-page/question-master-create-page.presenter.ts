import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { QuestionMasterCreatePageStore } from "./question-master-create-page.store";

@Injectable()
export class QuestionMasterCreatePagePresenter{
    readonly createQuestionMasterForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    constructor(private pageStore:QuestionMasterCreatePageStore){
        this.createQuestionMasterForm=pageStore.quesMasterForm
    }

    public get questionMasterHeader(){
        return this.createQuestionMasterForm.get('questionMasterHeader') as FormGroup
    }

    public get questionMasterFormArray(){
        return this.createQuestionMasterForm.get('questionMasterFormArray') as FormArray
    }

    public get subAnsFormArray(){
        return this.createQuestionMasterForm.get('subAnsFormArray') as FormArray
    }

    patchRow(row?:any){
        const fg = this.pageStore.buildQuestMasterMainFormArray();
        // const subQues=fg.get('subQuest') as FormArray
        // subQues.push(this.pageStore.buildSubQuestionArray())
        if(row){
            fg.patchValue(row);
        }
        this.questionMasterFormArray.push(fg);
    }

    addRowForManualEntry(){
        const fg = this.pageStore.buildQuestMasterMainFormArray();
        (fg.get('subQuest') as FormArray).push(this.pageStore.buildSubQuestionArray());
        this.questionMasterFormArray.push(fg);
        return fg;
    }

    removeRow(index:number){
        this.questionMasterFormArray.removeAt(index)
    }

    addSubAns(fg){
        (fg.get('subQuest') as FormArray).push(this.pageStore.buildSubQuestionArray());
    }

    removeSubAns(fa:FormArray,index:number){
        fa.removeAt(index)
    }

}