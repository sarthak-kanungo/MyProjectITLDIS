import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { QuestionMasterSearchPageStore } from "./question-master-search-page.store";

@Injectable()
export class QuestionMasterSearchPagePresenter{

    readonly questionMasterSearchForm: FormGroup


    constructor(private store:QuestionMasterSearchPageStore){
        this.questionMasterSearchForm = store.quesMasterSearchForm
    }

    public get questionMasterSearchHeaderForm(){
        return this.questionMasterSearchForm.get('questionMasterSearchHeader') as FormGroup
    }
}